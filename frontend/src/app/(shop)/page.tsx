import Link from "next/link";
import styles from "@/styles/Home.module.css";
import Image from "next/image";
import { Section } from "@/components/Section";
import { Slider } from "@/components/Slider";
import NewestProducts from "@/mock/home.json";
import { Card } from "@/components/Card";

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
                <div className={styles.profitTable}>
                    <div style={{ flex: "calc(50% - 4px) 0 0" }}>
                        <div className={styles.profitTableRow}>
                            <Card style={{ flex: "calc(50% - 4px) 0 0" }} title="1." subtitle="Trải nghiệm mua sắm" description="Trải nghiệm mua sắm được cá nhân hóa với ứng dụng Apple Store."/>
                            <Card style={{ flex: "calc(50% - 4px) 0 0" }} title="2." subtitle="Lãi suất trả góp thấp" description="Thanh toán lãi suất thấp, thời hạn lên đến 24 tháng tùy theo bạn chọn."/>
                        </div>
                        <div className={styles.profitTableRow}>
                            <Card style={{ flex: "calc(50% - 4px) 0 0" }} title="3." subtitle="Đổi cũ nâng cấp mới" description="Với các thiết bị cũ đủ điều kiện sẽ nhận được khoản hỗ trợ lên đời mới nhất."/>
                            <Card style={{ flex: "calc(50% - 4px) 0 0" }} title="4." subtitle="Miễn phí vận chuyển" description="Chúng tôi sẽ giao đến tận tay sản phẩm được gói kĩ càng hoàn toàn miễn phí."/>
                        </div>
                    </div>
                    <div className={styles.profitImageWrap}>
                        <div className={styles.profitTableRow} style={{ flexDirection: "column" }}>
                            <Image src="/policy/1.jpeg" alt="Policy 1" width={564} height={317} className={styles.profitImage}/>
                            <Image src="/policy/2.jpeg" alt="Policy 2" width={564} height={703} className={styles.profitImage}/>
                        </div>
                        <div className={styles.profitTableRow} style={{ flexDirection: "column" }}>
                            <Image src="/policy/3.jpeg" alt="Policy 3" width={564} height={703} className={styles.profitImage}/>
                            <Image src="/policy/4.jpeg" alt="Policy 4" width={564} height={317} className={styles.profitImage}/>
                        </div>
                    </div>
                </div>
            </Section>
        </>
    );
}
