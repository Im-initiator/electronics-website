import Link from "next/link";
import styles from "@/styles/Home.module.css";
import { Section } from "@/components/Section";
import { Slider } from "@/components/Slider";
import NewestProducts from "@/mock/home.json";

export default function Home() {
    return (
        <>
            <div className={styles.bannerWrap}>
                <video
                    src="/banner.mp4"
                    autoPlay
                    loop
                    muted
                    playsInline
                    width={1920}
                    height={1080}
                    className={styles.bannerVideo}
                />
                <div className={styles.bannerDescriptionWrap}>
                    <div className={styles.bannerDescriptionTitle}>VISION PRO</div>
                    <div className={styles.bannerDescriptionSubTitle}>Khai phá giác quan, mở cảnh giới mới</div>
                    <div className={styles.bannerDescriptionButtonWrap}>
                        <Link className={`${styles.bannerDescriptionButton} ${styles.bannerDescriptionButtonOutline}`} href='/'>Tìm hiểu thêm</Link>
                        <Link className={`${styles.bannerDescriptionButton} ${styles.bannerDescriptionButtonFill}`} href='/'>Mua ngay</Link>
                    </div>
                </div>
            </div>
            <Section title="Sản phẩm nổi bật">
                <Slider data={NewestProducts}/>
            </Section>
            <Section title="Quyền lợi khách hàng" subtitle="Thêm nhiều lý do để mua sắm cùng chúng tôi.">

            </Section>
        </>
    );
}
