# -*- coding: utf-8 -*-
import en

f = open(r"other.txt")
d = open("recognized.txt", "w")
un = open("unrecognized.txt", "w")
recognized = []
unrecognized = []

for aline in f:
    theword = aline[:-1]
    try:
        conj = en.verb.conjugate(theword, tense="past")
        recognized.append(conj) 
        print theword + " recognized! Past = " + conj
    except KeyError:
        unrecognized.append(theword)
        print theword + " NOT recognized!"

for item in recognized:
    d.write(item + "\n")
    
for notitem in unrecognized:
    un.write(notitem + "\n")

f.close()
d.close()
un.close()
        