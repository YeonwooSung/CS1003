//  A class for counting ngrams
//
//  See the starter file for example on how to use it
// 
//  There is really no need to modify anything in here!
//  The Comparable interface will be discussed in CS2001
//
//  Kasim Terzic, 10 Feb 2017


public class NgramCount implements Comparable<NgramCount> {

    public String ngram;
    public int count;

    // Public constructor
    public NgramCount(String name, int count)
    {
        this.count = count;
        this.ngram = name;
    }

    // The code below will allow Java to sort these objects
    // This is provided to ensure that all students use the
    // same sorting order and make 
    public int compareTo(NgramCount o) {
        if(this.count == o.count) 
            return o.ngram.compareTo(this.ngram);
        return this.count-o.count;
    }
}
