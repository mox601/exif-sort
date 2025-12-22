(ns exif-sort.exif-sort-root
  (:gen-class))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn greet-out
  "Callable entry point to the application."
  [data] 
  (str "Hello, " (or (:name data) "World") "!"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (greet {:name (first args)})))
