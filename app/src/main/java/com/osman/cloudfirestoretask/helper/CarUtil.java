/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.osman.cloudfirestoretask.helper;

import android.content.Context;

import com.osman.cloudfirestoretask.R;
import com.osman.cloudfirestoretask.model.Car;

import java.util.Random;

public class CarUtil {

    private static final String[] CARS_NAME = {
            "BMW",
            "Acura",
            "Aston Martin",
            "Bugatti",
            "Buick",
            "Bugatti",
            "Bentley",
            "Audi",
            "The Best",
    };

    private static final String[] CARS_IMAGES = {
            "https://images.pexels.com/photos/170811/pexels-photo-170811.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            "https://f1.media.brightcove.com/8/1078702682/1078702682_6004950245001_6004956161001-vs.jpg?pubId=1078702682&videoId=6004956161001",
            "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/pass/Transpo_G70_TA-518126.jpg",
            "https://drop.ndtv.com/albums/AUTO/hyundai-venue-subcompact-suv/640_640x480.jpg",
            "https://imgd.aeplcdn.com/600x337/cw/ec/33372/Kia-Seltos-Exterior-167737.jpg?wm=0",
            "https://images.pexels.com/photos/112460/pexels-photo-112460.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            "https://img.etimg.com/thumb/msid-61740239,width-643,imgsize-251731,resizemode-4/heres-why-the-mclaren-720s-worth-280000-is-a-difficult-car-to-love.jpg",
    };

    public static Car getRandomCar(Context context) {
        Car car = new Car();
        Random random = new Random();

        String[] cities = context.getResources().getStringArray(R.array.cities);
        String[] dates = context.getResources().getStringArray(R.array.dates);
        String[] pulishers = context.getResources().getStringArray(R.array.publishers);

        int[] prices = new int[]{1, 2, 3};

        car.setName(getRandomString(CARS_NAME, random));
        car.setCity(getRandomString(cities, random));
        car.setCategory("Car");
        car.setPhoto(getRandomString(CARS_IMAGES, random));
        car.setPrice(getRandomInt(prices, random));
        car.setPublicationDate(getRandomString(dates, random));
        car.setPublisherName(getRandomString(pulishers, random));

        return car;
    }

    public static String getPriceString(int priceInt) {
        switch (priceInt) {
            case 1:
                return "$";
            case 2:
                return "$$";
            case 3:
            default:
                return "$$$";
        }
    }

    private static String getRandomString(String[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

    private static int getRandomInt(int[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

}
