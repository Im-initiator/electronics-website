package com.electronics_store.service.impl;

import java.util.*;

import com.electronics_store.mapper.EmployeeMapper;
import com.electronics_store.model.dto.request.employee.CreateEmployeeByAdminDTO;
import com.electronics_store.model.entity.*;
import com.electronics_store.repository.*;
import com.electronics_store.utils.*;
import jakarta.transaction.Transactional;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.enums.RoleType;
import com.electronics_store.enums.State;
import com.electronics_store.enums.UserStatus;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.AccountMapper;
import com.electronics_store.mapper.DataMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.BaseDTO;
import com.electronics_store.model.dto.request.account.*;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.dto.response.account.GetAccountByAdminByAdminDTO;
import com.electronics_store.model.dto.response.account.UpdateAccountByUserDTO;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.jwt.JwtService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    JwtService jwtService;
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    TokenRepository tokenRepository;
    AccountMapper accountMapper;
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;
    BranchRepository branchRepository;
    //Lấy một Validator từ factory mặc định
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static String URL_USER_DEFAULT = "\\files_upload\\images\\userDefault.png";

    @Override
    public Optional<CreateAccountByUserRequestDTO> findByUserName(String userName) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ApiResponse<LoginResponseDTO> createAccountByUser(
            CreateAccountByUserRequestDTO createAccountByUserRequestDTO) {
        if (accountRepository.isExitingUserName(createAccountByUserRequestDTO.getUserName())) {
            throw new CustomRuntimeException(ErrorSystem.USER_NAME_IS_EXISTS);
        }
        if (accountRepository.isExitingEmail(createAccountByUserRequestDTO.getEmail())) {
            throw new CustomRuntimeException(ErrorSystem.EMAIL_IS_EXISTS);
        }
        RoleEntity role = roleRepository
                .findByName(RoleType.USER.name())
                .orElseThrow(() -> new NullPointerException("Role User not found"));
        AccountEntity accountEntity = DataMapper.toEntity(createAccountByUserRequestDTO, AccountEntity.class);
        accountEntity.setId(null);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRoles(Set.of(role));
        accountEntity.setThumbnail(URL_USER_DEFAULT);
        accountEntity.setStatus(UserStatus.ACTIVE);
        accountRepository.save(accountEntity);
        AccountEntity account = accountRepository
                .findByUserName(createAccountByUserRequestDTO.getUserName())
                .orElse(null);
        CustomUserDetails userDetails = new CustomUserDetails(account);
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        // save token
        TokenEntity token = TokenEntity.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(account)
                .build();
        tokenRepository.save(token);
        // generate response
        LoginResponseDTO response = LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return new ApiResponse<>(response, "create account successful");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> createAccountByAdmin(CreateAccountByAdminRequestDTO account) {
        if (accountRepository.isExitingUserName(account.getUserName())) {
            throw new CustomRuntimeException(ErrorSystem.USER_NAME_IS_EXISTS);
        }
        if (accountRepository.isExitingEmail(account.getEmail())) {
            throw new CustomRuntimeException(ErrorSystem.EMAIL_IS_EXISTS);
        }
        if (account.getRoleIds().isEmpty()) {
            throw new CustomRuntimeException(ErrorSystem.ROLE_NULL);
        }

        AccountEntity accountEntity = accountMapper.toEntity(account);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        Set<RoleEntity> roles = roleRepository.findByIdIn(account.getRoleIds());
        accountEntity.setRoles(roles);
        accountEntity.setStatus(UserStatus.ACTIVE);
        accountEntity.setThumbnail(URL_USER_DEFAULT);
        String[] names = {
                RoleType.EMPLOYEE.name(),
                RoleType.MANAGER.name()
        };
        accountEntity = accountRepository.save(accountEntity);
        if(roleRepository.isIdContainingName(account.getRoleIds(), Arrays.asList(names))) {
            if (account.getEmployee() != null && !ObjectUtils.isObjectEmpty(account.getEmployee())) {
                //validate thông tin nhân viên, phát hiện xem có ConstraintViolation nào được  tạo ra hay không
                //Nếu có lỗi valid trong nhân viên thì Set sẽ chứa ConstraintViolation chứa thông tin lỗi
                Set<ConstraintViolation<CreateEmployeeByAdminDTO>> employeeViolations = validator.validate(account.getEmployee());
                if (!employeeViolations.isEmpty()) {
                    //Lấy ra đối tượng đầu tiên trong Set và ném ra lỗi
                    throw new CustomRuntimeException(employeeViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
                }
                EmployeeEntity employee = employeeMapper.toEntity(account.getEmployee());
                BranchEntity branch = branchRepository.findByIdAndState(account.getEmployee().getBranchId(), State.ACTIVE)
                        .orElseThrow(() -> new CustomRuntimeException("Branch not found",HttpStatus.NOT_FOUND));
                employee.setBranch(branch);
                employee.setAccount(accountEntity);
                employeeRepository.save(employee);
                return new ApiResponse<>("create user success");
            }else {
                throw new CustomRuntimeException("Employee not found",HttpStatus.NOT_FOUND);
            }
        }

        return new ApiResponse<>("create user success");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<UpdateAccountByUserDTO> updateUserInformationByUser(
            UpdateAccountByUserRequestDTO updateAccountByUserRequestDTO) {
        if (accountRepository.isExitingUserName(updateAccountByUserRequestDTO.getUserName())) {
            throw new CustomRuntimeException(ErrorSystem.USER_NAME_IS_EXISTS);
        }
        if (accountRepository.isExitingEmail(updateAccountByUserRequestDTO.getEmail())) {
            throw new CustomRuntimeException(ErrorSystem.EMAIL_IS_EXISTS);
        }
        updateAccountByUserRequestDTO.setId(SecurityUtils.getPrincipalId());
        String filename = updateAccountByUserRequestDTO.getImage().getOriginalFilename();
        String image = null;
        AccountEntity account = accountRepository
                .findById(updateAccountByUserRequestDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        String oldImage = account.getThumbnail();
        try {
            DataMapper.map(updateAccountByUserRequestDTO, account);
            if (!FileUtils.isImageExisted(filename)) {
                image = FileUtils.saveImage(updateAccountByUserRequestDTO.getImage());
                account.setThumbnail(image);
            }
            account = accountRepository.save(account);
            if (image!= null && oldImage != null && !oldImage.equals(URL_USER_DEFAULT)&&FileUtils.checkPath(oldImage)) {
                if (!FileUtils.deleteImage(oldImage)) {
                    FileUtils.deleteImage(image);
                    throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
                }
            }
            UpdateAccountByUserDTO accountResponse = DataMapper.toDTO(account, UpdateAccountByUserDTO.class);
            return new ApiResponse<>(accountResponse, "Update account successful");
        } catch (Exception e) {
            if (image != null) {
                if (FileUtils.checkPath(image)) {
                    FileUtils.deleteImage(image);
                }
            }
            throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> findAllAccountActiveByAdmin(Map<String, String> request) {
        try {
            State state = State.convert(Integer.parseInt(request.getOrDefault("state","1")));
            UserStatus status = UserStatus.convert(Integer.parseInt(request.getOrDefault("user_status","1")));
            Pageable pageable = RequestUtils.getPageable(request);
            Page<AccountEntity> pageAccount = null;
            if (!request.containsKey("name")) {
                pageAccount = accountRepository.findAllAccountActiveOrderByCreatedDate(state, status, pageable);
            } else {
                String name = request.get("name");
                pageAccount = accountRepository.findAllAccountActiveAndNameContainOrderByCreatedDate(
                        state, status, name, pageable);
            }
            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, pageAccount, accountMapper, a -> accountMapper.toGetAccountByAdminDTO((AccountEntity) a));
            return new ApiResponse<>(result, "Get all account success");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> updateAccountByAdmin(Long id, UpdateAccountByAdmin account) {
        AccountEntity accountEntity = accountRepository
                .findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        accountEntity.setStatus(UserStatus.convert(account.getStatus()));
        accountRepository.save(accountEntity);
        return new ApiResponse<>("Update account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> updateAccountByAdmin(BaseDTO baseAccount) {
        AccountEntity accountEntity = accountRepository
                .findById(baseAccount.getId())
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        accountEntity.setState(State.convert(baseAccount.getState()));
        accountRepository.save(accountEntity);
        if (baseAccount.getState() == 0) {
            return new ApiResponse<>("Remove account success");
        }
        return new ApiResponse<>("Restore account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> updateAccountByAdmin(Long id, State state) {
        AccountEntity accountEntity = accountRepository
                .findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        accountEntity.setState(state);
        accountRepository.save(accountEntity);
        if (state == State.DELETE) {
            return new ApiResponse<>("Remove account success");
        }
        return new ApiResponse<>("Restore account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<GetAccountByAdminByAdminDTO> getAccountByAdmin(Long id) {
        AccountEntity accountEntity = accountRepository
                .findAccountById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        EmployeeEntity employee = accountEntity.getEmployee();
        return new ApiResponse<>(accountMapper.toGetAccountByAdminDTO(accountEntity), "Get account success");
    }
}
