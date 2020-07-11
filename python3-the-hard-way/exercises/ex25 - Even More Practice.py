def break_words(stuff):
	"""Splits words that are separated by spaces."""
	words = stuff.split(" ")
	return words

def sort_words(words):
	"""Sorts a list of words."""
	return sorted(words)

def print_first_word(words):
	"""Prints and removes the first word of a list."""
	word = words.pop(0)
	print(word)

def print_last_word(words):
	"""Prints and removes the last word of a list."""
	word = words.pop(-1)
	print(word)

def sort_sentence(sentence):
	"""Sorts a sentence."""
	words = break_words(sentence)
	return sort_words(words)

def print_first_and_last(sentence):
	"""Prints the first and last words of a sentence."""
	words = break_words(sentence)
	print_first_word(words)
	print_last_word(words)

def print_first_and_last_sorted(sentence):
	"""Sorts a sentence and then prints the first and last words."""
	words = sort_sentence(sentence)
	print_first_word(words)
	print_last_word(words)

# foo_bar = __import__("ex25 - Even More Practice")
# execfile("ex25 - Even More Practice.py")