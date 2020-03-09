(ns ringneck.logic
	(:require
		[clojure.set :refer :all]
		[clojure.pprint :refer [pprint]]))


(defn what-direction [move head]
	(let [direction (map - move head)
		  x (first direction)
		  y (second direction)]
		(if (= y 0)
			(if (= x 1) "right" "left")
			(if (= y 1) "down" "up"))))

(defn possible-moves [body]
	(let [
			directions [[1,0],[-1,0],[0,1],[0,-1]]
			head (first body)
			all-moves (map (fn [direction] (map + direction head)) directions)
		 ]
	(vec (difference (set all-moves) (set body)))))

(defn calc-move [params]
	; (pprint (:board params))
	(let [ 
			board (:board params)
			height (:height board)
			width (:width board)
			me (:you params)
			my-body (map vals (:body me))
			my-head (first my-body)
		 	moves (possible-moves my-body)
		 ]
		(what-direction (rand-nth moves) my-head)))