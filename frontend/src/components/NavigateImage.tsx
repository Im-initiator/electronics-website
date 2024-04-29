import { FC } from "react";
import Link from "next/link";
import Image from "next/image";

interface NavigateImageProps {
    className?: string,
    href: string,
    src: string,
    alt: string
}

interface NavigateImageFillProps extends NavigateImageProps {
    fill: boolean
}

interface NavigateImageSizeProps extends NavigateImageProps {
    height: number,
    width: number
}

function imageIsFill(image: NavigateImageFillProps | NavigateImageSizeProps): image is NavigateImageFillProps {
    return (image as NavigateImageFillProps).fill !== undefined;
}

export const NavigateImage: FC<NavigateImageFillProps | NavigateImageSizeProps> = (props) => {
    const { href, src, alt, className } = props;

    if (imageIsFill(props)) {
        return (
            <Link href={href} className={className}>
                <Image src={src} alt={alt} fill/>
            </Link>
        );
    }

    const { height, width } = props;

    return (
        <Link href={href} className={className}>
            <Image src={src} alt={alt} height={height} width={width}/>
        </Link>
    );
}