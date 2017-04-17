#!/usr/local/bin/python
# coding: UTF-8


import re
from copy import deepcopy

def release_list(a):
   del a[:]
   del a

def isConverged(t_egf1, t_egf2):
    result = 0
    for english_word in english_words:
    	for foreign_word in foreign_words:
    		delta = (t_egf1[english_word, foreign_word]- t_egf2[english_word, foreign_word])**2
    		result+=delta
    print(result ** 0.5 )
    return result**0.5 <.001



english_words_r = list()
foreign_words_r = list()
english_sentences = list()
foreign_sentences = list()


path1 = '/home/yash/Downloads/ir/code/eng10'
file1 = open(path1,'r')
englishsentences = list()

for line in file1:
	englishsentences.append(line.split('.'))

for lines in englishsentences:
	for line in lines:
		english_sentences.append(line)
# print(englishsentences_new)

path2 = '/home/yash/Downloads/ir/code/french10'
file2 = open(path2,'r')
foreignsentences = list()

print("1")
for line in file2:
	foreignsentences.append(line.split('.'))

for lines in foreignsentences:
	for line in lines:
		foreign_sentences.append(line)
print("2")
# print(englishsentences_new)
release_list(englishsentences)
release_list(foreignsentences)
print("3")
# english_sentences.append("the house")
# english_sentences.append("the book")
# english_sentences.append("a book")
# foreign_sentences.append("das haus")
# foreign_sentences.append("das buch")
# foreign_sentences.append("ein buch")

# english_sentences.append("hi, there,")
# foreign_sentences.append("abra ca dabra")
en = set()
f = set()

for sentences in english_sentences:
	wordList = re.sub("[^\w]", " ",  sentences).split()
	for words in wordList:
		en.add(words)
for sentences in foreign_sentences:
	wordList = re.sub("[^\w]", " ",  sentences).split()
	for words in wordList:
		f.add(words)

	
english_words = list(en)
foreign_words = list(f)

len_english_words = len(english_words)
len_foreign_words = len(foreign_words)
print(len_english_words)
print(len_foreign_words)
# print("hi "+str(len_english_words))
# print(len_foreign_words)
num_english_sent = len(english_sentences)
print(num_english_sent)
print("5")
t_egf = {}
t_egf_prev = {}

for english_word in english_words:
	for foreign_word in foreign_words:
		t_egf[english_word, foreign_word] = float(1)/float(len_english_words)
		# print(english_word+" "+foreign_word + ": "+str(t_egf[english_word, foreign_word]))
# print(t_egf)
# print(t_egf['I','déclare'])
i=3
while(1):
	print("6")
	count= {}
	total = {}
	t_egf_prev = deepcopy(t_egf)
	# print(t_egf_prev)
	for english_word in english_words:
		for foreign_word in foreign_words:
			count[english_word, foreign_word] = 0
	for foreign_word in foreign_words:
		total[foreign_word] = 0
	j=0
	for english_sentence in english_sentences:
		# for foreign_sentence in foreign_sentences:
			s_total = {}
			wordse = english_sentences[j].split()
			wordsf = foreign_sentences[j].split()	
			# print(wordse)
			# print(wordsf)
			j=j+1
			# print("Prog: "+str(j*100/num_english_sent)+"\n")
			for english_word in wordse:
				s_total[english_word] = 0
				for foreign_word in wordsf:
					print(english_word+"|"+foreign_word)
					s_total[english_word]+= t_egf[english_word,foreign_word]
			for english_word in wordse:
				for foreign_word in wordsf:
					count[english_word, foreign_word] += t_egf[english_word,foreign_word]/s_total[english_word]
					total[foreign_word] += t_egf[english_word, foreign_word]/s_total[english_word]

	for foreign_word in foreign_words:
		for english_word in english_words:
			t_egf[english_word, foreign_word] = count[english_word,foreign_word]/total[foreign_word]

	
	# print(t_egf)
	# print(t_egf_prev)

	# for english_word in english_words:
		# for foreign_word in foreign_words:
			# print(english_word+" "+foreign_word + ": "+str(t_egf[english_word, foreign_word]))
			# print("1")
	
	# print(t_egf)
	# print(t_egf_prev)
	
	if(isConverged(t_egf, t_egf_prev)):
		break
	# print("\n")
	i=i-1

print("OUT OF BREAK!")			
