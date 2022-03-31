## Lab 11 - March 31, 2022

In this lab you will work with
streams, lists, and maps.

## Jumble Permutation Generator

The traditional way to solve
a Jumble puzzle in a CS2 class
is to generate all possible permutations
of N letters. This is traditionally
done by a relatively easy to understand
recursive function that keeps adding to
an `ArrayList<String>`. Then, the
strings in the arraylist are checked
against a dictionary.

Instead, I use a non-recursive permutation
generator and send the strings
so that they can be read from a `Stream<String>`.
One motivation for this approach is that for
non-trivial N, there may not be space to store
an array of N!, N character strings. However, since
dictionary lookups are fast, it might not
take unreasonably long to do N! dictionary lookups.

In the code, look in package `cs121` to find
the `JumbleStreamSolver` that
consumes this stream. In `solve()`, the code
currently uses a `Stream<String>` to generate
a `List<String>` of dictionary words that
have the right set of letters. What remains
is to pick the most common word.


### Lab assignment, Part 1 - Jumble

Note that the `WordFreqMap` contains
information about word commonness. Use this
information to pick the most common word in the
generated list. Re-write the code so `solve()`
returns a string and `main()` prints it out.

## Switch Roles

This is a good time for the pilot
and navigator to switch roles.

Or, have one partner pilot the writing of the
method described in "Common Elements" below,
while the other partner works on writing
code to use the method. Make sure you
agree on what the function signature will be
(what its name is and what kind of argument it
should take: clearly, it should return a `String`).

## Lab Assignment: Part 2 - Spelling Corrector

Now look in the `spelling` package at the `CandidateGenerator`
class. Again, we have a `WordFreqMap` available to
help us pick the most common word emerging from a `Stream<String>`.

This time, the setting is taking a non-dictionary word and finding
the most common dictionary word that is at edit distance 1 from the
non-dictionary word. Studies have shown that 80% of naturally
occurring misspellings can be corrected in the manner.

What our `Stream<String>` provides this time is a
sequence of strings that are at edit distance one
from the non-dictionary word. These are again
filtered by doing dictionary lookups.

## Common elements

So, just like before, we want to

* convert a sequence of strings into a sequence of
word-frequency pairs and
* pick the word paired with the highest frequency.

Note, in package `cs121` there is a class that you
might find useful called `WordFrequency` that
implements the `Comaparable<WordFrequency>` interface
that simply provides a natural order of `WordFrequency` objects in the
order of lowest to highest frequency.

* you could sort a sequence of `WordFrequency` objects and
pick the last item on the list.
* or, you could do a linear search to simply find the
`WordFrequency` object with the maximum frequency.

You **could** write a method that given something like a
`Stream<WordFreqency>` or a `List<WordFreqency>` returns
the word with the highest frequency and uses it for both.