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
    (is (= 1 1))
    (is (= "f/1.8" 
           (get 
            (exif/exif-for-filename 
             "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg") 
            "F-Number")))))
