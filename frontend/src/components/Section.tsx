import { FC, ReactNode } from "react";
import styles from "@/styles/Section.module.css"

interface SectionProps {
    title: string,
    subtitle?: string,
    children?: ReactNode
}

export const Section: FC<SectionProps> = ({ title, subtitle, children }) => {
    return (
        <section className={styles.sectionContainer}>
            <h1 className={styles.sectionTitle}>
                {title}
            </h1>
            <h3 className={styles.sectionSubTitle}>
                {subtitle}
            </h3>
            <div className={styles.sectionContent}>
                {children}
            </div>
        </section>
    );
}