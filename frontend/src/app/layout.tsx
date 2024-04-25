import type { Metadata } from "next";
import { ReactNode } from "react";
import { Montserrat } from 'next/font/google';
import "./globals.css";

export const metadata: Metadata = {
  title: "Cửa hàng Apple",
  description: "Đại lý phân phối sản phẩm của Apple",
};

const font = Montserrat({
    subsets: ['vietnamese'],
    display: 'swap'
});

export default function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
  return (
    <html lang="vi">
      <body className={font.className}>
        {children}
      </body>
    </html>
  );
}
