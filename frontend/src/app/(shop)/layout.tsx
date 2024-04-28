import { Header } from "@/components/Header";
import { ReactNode } from "react";

export default function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
    return (
        <>
            <Header/>
            {children}
        </>
    );
}