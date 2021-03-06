package com.rea.recursion

import scala.annotation.tailrec

// Taken from http://tmorris.net/posts/scala-exercises-for-beginners/index.html

/**
 * Ok here are the rules.
 *
 * You can't use any of the standard list functions, like `map`, `filter`, `flatMap`, `append`, `:::`, `:+`, etc.
 * 
 * But you can always use `::` to construct a new list by prepending an element to another list.
 *
 * You CAN and are encouraged to use the solutions from the exercises below to solve the harder
 * ones towards the end.
 *
 * Keep an eye out for repetition and similarities between your answers.
 *
 * REMEMBER: Follow the types, they almost always guide you to the solution.  If it compiles and looks a little
 * too simple, it's probably correct.  As Sherlock Holmes once said, "Each one is suggestive, together they are
 * most certainly conclusive."
 *
 * See if you can make your solution tail recursive, where possible.
 *
 */

object RecursionExercises {

  def plusOne(n: Int): Int = n + 1

  def minusOne(n: Int): Int = n - 1

  // Add two non-negative Integers together.  You are only allowed to use plusOne and minusOne above
  def add(a: Int, b: Int): Int = {
    b match {
      case 0 => a
      case _ => add(plusOne(a), minusOne(b))
    }
  }

  // You are not permitted to use any list functions such as map, flatMap, ++, flatten etc
  def sum(l: List[Int]): Int = {
    @tailrec
    def sumHelper(l: List[Int], currentSum: Int): Int = l match {
      case Nil => currentSum
      case _ => sumHelper(l.tail, currentSum + l.head)
    }
    sumHelper(l, 0)
  }

  //Again no list functions are permitted for the following
  def length[A](x: List[A]): Int = {
    @tailrec
    def lengthHelper(x: List[A], currentLength: Int): Int = x match {
      case Nil => currentLength
      case _ => lengthHelper(x.tail, 1 + currentLength)
    }
    lengthHelper(x, 0)
  }

  // Do you notice anything similar between sum and length? Hmm...

  // Mapping over a list.  You are given a List of type A and a function converting an A to a B
  // and you give back a list of type B.  No list functions allowed!
  def map[A, B](x: List[A], f: A => B): List[B] = x match {
    case Nil => Nil
    case _ => f(x.head)::map(x.tail, f)
  }

  // Given a function from A => Boolean, return a list with only those item where the function returned true.
  def filter[A](x: List[A], f: A => Boolean): List[A] = x match {
    case Nil => Nil
    case _ => if (f(x.head)) x.head::filter(x.tail, f) else filter(x.tail, f)
  }

  // This pattern should be familiar by now... psst... look at add.
  def append[A](x: List[A], y: List[A]): List[A] = x match {
    case Nil if y.nonEmpty => y.head::append(x, y.tail)
    case Nil => Nil
    case _ => x.head::append(x.tail, y)
  }

  // Flatten a list of lists to a single list.  Remember you can't use list.flatten.  Can you use a previous
  // solution to solve this one?
  def flatten[A](x: List[List[A]]): List[A] = {
    if(x.isEmpty) {
      Nil
    } else if(x.head.tail.nonEmpty) {
      append(x.head, flatten(x.tail))
    } else {
      x.head.head::flatten(x.tail)
    }
  }

  // Follow the types.  You've done a great job getting here. Follow the types.
  def flatMap[A, B](x: List[A], f: A => List[B]): List[B] = {
    if(x.isEmpty){
      Nil
    } else {
      append(f(x.head), flatMap(x.tail, f))
    }
  }

  // Maximum of the empty list is 0
  def maximum(x: List[Int]): Int = {
    @tailrec
    def maximumHelper(x: List[Int], runningMax: Int) : Int = x match {
      case Nil => runningMax
      case _ => maximumHelper(x.tail, Math.max(x.head, runningMax))
    }
    maximumHelper(x, 0)
  }

  // Reverse a list
  def reverse[A](x: List[A]): List[A] = {
    @tailrec
    def reverseHelper(x: List[A], runningList: List[A]) : List[A] = x match {
      case Nil => runningList
      case _ => reverseHelper(x.tail, x.head::runningList)
    }
    reverseHelper(x, Nil)
  }
}
