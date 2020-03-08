(ns ringneck.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response]]
            [clojure.pprint :refer [pprint]]))

; heartbeat
(defn ping [request]
	(response {:status 1}))

; end of game
(defn end [request]
	(response {:status 1}))

; start of new game
(defn start [request]
	(pprint (:params request))  ;; debug
	(response {
			:color "#FF6D19"
			:headType "tongue"
			:tailType "fat-rattle"
		}))

; take turn in game
(defn move [request]
  (pprint (:params request)) ;; debug
  (response {
  		:move "up"
  		:shout "ssSSSsssS!!"
  	})

(defroutes app-routes
  (GET "/" [] ping)
  (POST "/ping" [] ping)
  (POST "/start" [] start)
  (POST "/move" [] move)
  (POST "/end" [] end)
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
