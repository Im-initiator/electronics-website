"use client";

import { Dict } from "@/interface/Dict";
import { FC, useMemo, useReducer, useEffect } from "react";
import styles from "@/styles/Slider.module.css";
import { NavigateImage } from "./NavigateImage";

interface SliderProps {
    data: Dict
}

type SliderAction = { type: "INCREMENT" } | { type: "DECREMENT" } | { type: "SET", value: number };

export const Slider: FC<SliderProps> = ({ data }) => {
    const listTab = useMemo(() => Object.keys(data), [data]);
    const numberOfTab = useMemo(() => listTab.length, [listTab]);

    const sliderReducer = (state: number, action: SliderAction): number => {
        switch (action.type) {
        case "INCREMENT":
            return state + 1 < numberOfTab ? state + 1 : 0;
        case "DECREMENT":
            return state - 1 >= 0 ? state - 1 : numberOfTab - 1;
        case "SET":
            return action.value >= 0 && action.value< numberOfTab ? action.value : state;
        default:
            return state;
        }
    }

    const [currentTab, setCurrentTab] = useReducer(sliderReducer, 0);

    useEffect(() => {
        const changeImage = setTimeout(() => setCurrentTab({ type: "INCREMENT" }), 3000);

        return () => clearTimeout(changeImage);
    }, [currentTab]);

    return (
        <div className={styles.SliderContainer}>
            <div className={styles.SliderTabWrap}>
                {
                    listTab && listTab.map(
                        (value, index) => (
                            <div
                                className={`${styles.SliderTab} ${listTab[currentTab] === value ? styles.SliderTabActive : ""}`}
                                key={index}
                                onClick={() => setCurrentTab({ type: "SET", value: index })}>
                                {value}
                            </div>
                        )
                    )
                }
            </div>
            <div className={styles.SliderContent}>
                <div className={styles.SliderContentWrap}>
                    <div className={styles.SliderContentItemWrap}>
                        <NavigateImage href="/" src={data[listTab[currentTab]][0]} fill alt="Promotion Image"/>
                    </div>
                </div>
                <div className={styles.SliderContentWrap}>
                    <div className={styles.SliderContentItemWrap}>
                        <NavigateImage href="/" src={data[listTab[currentTab]][1]} fill className={styles.SliderContentItemRight} alt="Promotion Image"/>
                    </div>
                    <div className={styles.SliderContentItemWrap}>
                        <NavigateImage href="/" src={data[listTab[currentTab]][2]} fill className={styles.SliderContentItemRight} alt="Promotion Image"/>
                    </div>
                </div>
            </div>
        </div>
    );
}