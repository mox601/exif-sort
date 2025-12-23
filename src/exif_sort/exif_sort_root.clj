(ns exif-sort.exif-sort-root
  (:gen-class)
  (:require [clojure.pprint :as pprint]
            [exif-processor.core :as exif]
            [clojure.java.shell :as shell]
            [clojure.data.json :as json])
  
  (:import (java.io File)))

(defn dji-creation-date [path]
  (let [{:keys [out err exit]}
        (shell/sh "ffprobe"
                  "-v" "quiet"
                  "-show_entries" "format_tags=creation_time"
                  "-of" "json"
                  path)]
    (when (zero? exit)
      (get-in (json/read-str out :key-fn keyword)
              [:format :tags :creation_time]))))

;; fallback
(def date-patterns
  [#"(20\d{2})[-_.](\d{2})[-_.](\d{2})"
   #"(20\d{2})(\d{2})(\d{2})"])

(defn extract-date-from-filename
  [^File file]
  (let [name (.getName file)]
    (some
     (fn [re]
       (when-let [[_ y m d] (re-find re name)]
         (format "%s-%s-%s" y m d)))
     date-patterns)))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn greet-out
  "Callable entry point to the application."
  [data] 
  (str "Hello, " (or (:name data) "World") "!"))

(comment
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (greet {:name (first args)}))) 
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [file-to-extract-1 "D:\\Dati\\Foto\\digitali\\2025\\2025_11\\fairphone\\2025-11-07 09.27.53.jpg"
        file-to-extract-2 "D:\\Dati\\Foto\\digitali\\2025\\2025_12\\zv-1\\2025_12_19 Il Circo dei Bambini\\C7158.MP4"
  ;; TODO implement and use a fallback for DJI files
        file-to-extract-3 "D:\\Dati\\Foto\\digitali\\2025\\2025_12\\action 4\\DJI_20251206105349_0093_D.MP4"]
   ;;(pprint/pprint (exif/exif-for-filename file-to-extract-3))
    (pprint/pprint (extract-date-from-filename (java.io.File. file-to-extract-3)))))
