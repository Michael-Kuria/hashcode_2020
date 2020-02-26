# hashcode_2020

## Online Qualification Round
This is an optimization problem. According to the problem definition for one to achieve a high score, you have to find away to scan as
many books as possible ensuring that the one scanned have the highest score in that Library.

## Approaches Taken
a) Order Libraries according to their sign-up time.(Expected score **22,000,000+ points** if the following is observed)
- This should be done in an ascending order
- Ensure that the books are ordered according to their score in descending order.
- Ensure that no book is scanned twice

b) Take a look at each input data set and observe the pattern in them and try to come up with ideas that can increase the score.(Expected score **25,000,000+ points** if the following is observed)

- For `b_read_on.txt` the score for each book is 100 and the number of books in each library is 1000, thus the above described approach
will work fine.
- For `d_tough_choices.txt` the sign-up days are equal 2 but the number of books in each library are different. For this case 
we can scan all the libraries and ensure that each library has distinct books while also computing its score, then order them
in descending order. This can be done severally while each time a different starting point is chosen.
- For `c_incunabula.txt` , `e_so_many_books.txt` and `f_libraries_of_the_world.txt` I ran out of ideas. I used the approach
(a) and then shuffled the libraries 1500 time and picked the highest score.


