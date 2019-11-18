(deftemplate phone(slot brand) (slot price) (slot battery)(slot model))
(deftemplate inputdata(slot brand) (slot price) (slot battery))

(deffacts phoneslist (phone(brand Apple)(price 50000)(battery 3000)(model Iphone10))
    (phone(brand OnePlus)(price 42000)(battery 4000)(model 7))
    (phone(brand MI)(price 20000)(battery 5000)(model Note7Pro))
    )

(defrule start
    =>
    (open "C:\\Users\\sengh\\workspace\\JessBoyz\\src\\input.txt" data "r")
    (bind ?brand (read data))
    (bind ?price (read data))
    (bind ?battery (read data))
    (close data)
    (assert (inputdata(brand ?brand)(price ?price)(battery ?battery)))
    )

(defrule findphone
    (inputdata (brand ?brandchosen) (price ?pricechosen) (battery ?batterychosen))
    (phone (brand ?brand) (price ?price) (battery ?battery)(model ?model))
    (or (test (= ?brand ?brandchosen)) (test (= ?brandchosen nil)))
    (test (> ?battery ?batterychosen))
    (test (< ?price ?pricechosen))
    =>
    (printout t "Phone : "?brand" : "?model crlf)
    )

(reset)
(run)



(deffunction getresults
    (return (result(brand ?brand) (price ?price) (battery ?battery)(model ?model)))
)



(defrule findphone4
    (phone (brand ?brand) (price ?price) (battery ?battery)(model ?model))
    =>
    (printout t "Phone : "?brand" : "?model crlf)
    )
