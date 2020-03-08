(defproject ringneck "1.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
  															[compojure "1.6.1"]
  															[ring/ring-core "1.6.3"]
  															[ring/ring-json "0.5.0"]
  															[ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler ringneck.handler/app
         :auto-reload? true
         :auto-refresh? true})
