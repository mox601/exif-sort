(ns exif-sort.exif-sort-root-test
  (:require [clojure.test :refer [deftest is testing]]
            [exif-processor.core :as exif]
            [exif-sort.exif-sort-root :as sut])) ; system under test

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))
    (is (= "Hello, Mox!" (exif-sort.exif-sort-root/greet-out {:name "Mox"})))))

(deftest a-nother-test
  (testing "FIXME, I fail."
    ;; fairphone
    (is (= "f/1.8" 
           (get 
            (exif/exif-for-filename 
             "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg") 
            "F-Number")))
    (is (= "2025:11:07 09:27:53"
           (get
            (exif/exif-for-filename
             "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg")
            "Date/Time Original")))
    (is (= "2025:11:07 09:27:53"
           (get
            (exif/exif-for-filename
             "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg")
            "Date/Time")))
    (is (= "2025:11:07 09:27:53"
           (get
            (exif/exif-for-filename
             "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg")
            "Date/Time Digitized")))
    ;; iphone
    (is (= "2015:12:17 22:53:26"
           (get
            (exif/exif-for-filename
             "G:\\backup giulia 2021-03-28\\Dati\\foto\\2017-03-16 i phone\\IMG_3497.JPG")
            "Date/Time Digitized")))
    ;; dji videos
    (is (= {} 
           (exif/exif-for-filename "D:\\Dati\\Foto\\digitali\\2025\\2025_12\\action 4\\DJI_20251206105349_0093_D.MP4")))
    
    ;; can use ffprobe for dji videos
    (is (= "2025-12-06T09:53:50.000000Z"
           (sut/dji-creation-date "D:\\Dati\\Foto\\digitali\\2025\\2025_12\\action 4\\DJI_20251206105349_0093_D.MP4")))
    (is (= "2025-12-06" 
           (sut/extract-date-from-filename (java.io.File. "D:\\Dati\\Foto\\digitali\\2025\\2025_12\\action 4\\DJI_20251206105349_0093_D.MP4"))))))
