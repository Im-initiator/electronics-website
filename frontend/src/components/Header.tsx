"use client";

import { FC, useState } from "react";
import { IoIosSearch } from "react-icons/io";
import { GiHamburgerMenu } from "react-icons/gi";
import { MdOutlineShoppingBag } from "react-icons/md";
import { ImCross } from "react-icons/im";
import Link from "next/link";
import styles from "@/styles/Header.module.css";
import { NavigateImage } from "./NavigateImage";

export const Header: FC = () => {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

    return (
        <header className={styles.headerContainer}>
            <nav className={styles.headerWrap}>
                <NavigateImage href="/" className={styles.navItem} src="/logo.png" alt="Apple Logo" height={20} width={20}/>
                <div className={`${styles.navWrap} ${mobileMenuOpen ? styles.navMobileDown : styles.navMobileUp}`}>
                    <ImCross className={styles.menuClose} onClick={() => setMobileMenuOpen(false)}/>
                    <Link href="/" className={styles.navItem}>Cửa hàng</Link>
                    <Link href="/" className={styles.navItem}>Mac</Link>
                    <Link href="/" className={styles.navItem}>iPad</Link>
                    <Link href="/" className={styles.navItem}>iPhone</Link>
                    <Link href="/" className={styles.navItem}>Vision</Link>
                    <Link href="/" className={styles.navItem}>Airpods</Link>
                    <Link href="/" className={styles.navItem}>Hỗ trợ</Link>
                </div>
                <div className={styles.navIconWrap}>
                    <div className={styles.navItemSvg}>
                        <IoIosSearch/>
                    </div>
                    <div className={styles.navItemSvg}>
                        <MdOutlineShoppingBag/>
                    </div>
                    <div className={`${styles.navItemSvg} ${styles.menuToggle}`} onClick={() => setMobileMenuOpen(true)}>
                        <GiHamburgerMenu/>
                    </div>
                </div>
            </nav>
        </header>
    );
}