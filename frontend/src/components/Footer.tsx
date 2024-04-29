import Image from "next/image";
import { FC } from "react";
import styles from "@/styles/Footer.module.css";
import { FaFacebook, FaGithub, FaLinkedin, FaEnvelope } from "react-icons/fa";
import Link from "next/link";

export const Footer: FC = () => {
    return (
        <footer className={styles.footerContainer}>
            <div className={styles.footerItem}>
                <Image src="/logo.png" height={48} width={48} alt="Logo"/>
                <div className={styles.footerTitle}>Apple Shop</div>
                <div>Tech stack: Spring, MySql, NextTs, Docker</div>
            </div>
            <div className={styles.footerItem}>
                <div className={styles.footerSubTitle}>Lê Tấn Phát</div>
                <Link href="/" className={styles.footerIcon}><FaFacebook /><span>: Lê Tấn Phát</span></Link>
                <Link href="/" className={styles.footerIcon}><FaGithub /><span>: ltphat2204</span></Link>
                <Link href="/" className={styles.footerIcon}><FaLinkedin /><span>: Lê Tấn Phát</span></Link>
                <Link href="/" className={styles.footerIcon}><FaEnvelope /><span>: ltphat2204@gmail.com</span></Link>
            </div>
            <div className={styles.footerItem}>
                <div className={styles.footerSubTitle}>Nguyễn Ngọc Lan</div>
                <Link href="/" className={styles.footerIcon}><FaFacebook /><span>: Nguyễn Ngọc Lan</span></Link>
                <Link href="/" className={styles.footerIcon}><FaGithub /><span>: ltphat2204</span></Link>
                <Link href="/" className={styles.footerIcon}><FaLinkedin /><span>: Nguyễn Ngọc Lan</span></Link>
                <Link href="/" className={styles.footerIcon}><FaEnvelope /><span>: ltphat2204@gmail.com</span></Link>
            </div>
        </footer>
    );
}