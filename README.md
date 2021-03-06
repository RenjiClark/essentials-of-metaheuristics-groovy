essential-metaheuristics-groovy
===============================

Implementation in Groovy of many of the algorithms in Sean Luke's Essentials of Metaheuristics

My goal here is to implement several of the key algorithms in 
[Sean Luke's book Essentials of Metaheuristics]([http://www.lulu.com/shop/sean-luke/essentials-of-metaheuristics/paperback/product-15028803.html "Link to the book on Lulu.com") 
in Groovy. My initial goal is to both see what these algorithms look like in Groovy (I'm used to implementing them
in Java and, in a few cases, Ruby), and to provide some useful examples for my Evolutionary Computation and Artificial
Intelligence course.

Searchers I've implemented:
   * A simple implementation of Gradient Ascent for functions of one variable.
   * A simple hill-climber
   * A steepest ascent hill-climber
   * A steepest ascent hill-climber with replacement

Bit-string problems I've implemented:
   * OnesMax
   * LeadingOnes
   * LeadingOnesBlocks
   * (Binary) Trap
   * HIFF (Hierarchical If-and-only_if)

Real-valued vector problmes I've implemented:
   * Sum
   * Rastrigin
