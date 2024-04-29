import { CSSProperties, FC } from "react";
import styles from "@/styles/Card.module.css";

interface CardProps {
    title: string,
    subtitle: string,
    description: string,
    style?: CSSProperties
}

export const Card: FC<CardProps> = ({ title, subtitle, description, style }) => {
    return (
        <div className={styles.cardWrap} style={style}>
            <div className={styles.cardTitle}>{title}</div>
            <div className={styles.cardSubTitle}>{subtitle}</div>
            <div className={styles.cardDescription}>{description}</div>
        </div>
    );
}