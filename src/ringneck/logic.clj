(ns ringneck.logic
	(:require
		[clojure.set :refer :all]
		[clojure.pprint :refer [pprint]]))


(def test-board
	{:game {:id "4997848f-8205-4fe7-8fe0-2d0233a9227a"},
 :turn 5,
 :board
 {:height 15,
  :width 15,
  :food
  [{:x 13, :y 14}
   {:x 2, :y 3}
   {:x 5, :y 14}
   {:x 7, :y 9}
   {:x 14, :y 8}
   {:x 2, :y 4}
   {:x 12, :y 9}
   {:x 11, :y 8}
   {:x 2, :y 6}
   {:x 7, :y 4}],
  :snakes
  [{:id "e6811c81-1403-429d-99b8-4c5eeb3db0a7",
    :name "Snek",
    :health 95,
    :body [{:x 6, :y 14} {:x 7, :y 14} {:x 7, :y 13}]}]},
 :you
 {:id "e6811c81-1403-429d-99b8-4c5eeb3db0a7",
  :name "Snek",
  :health 95,
  :body [{:x 6, :y 14} {:x 7, :y 14} {:x 7, :y 13}]}})

(defn in? 
  "true if coll contains elm"
  [coll elm]  
  (some #(= elm %) coll))

(defn move-self [next-pos game-state]
	(let [got-food (in? (:food (:board game-state)) next-pos)
				moved-head (update-in 
											game-state 
											[:you :body] 
											(fn [body] (concat [next-pos] body)))
				calced-health (update-in moved-head [:you :health] (fn [health] (if got-food 100 (dec health))))
				moved-tail (if got-food calced-health (update-in calced-health [:you :body] (fn [body] (drop-last body))))]
	moved-tail))

(defn score-board [board]
	1000)

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