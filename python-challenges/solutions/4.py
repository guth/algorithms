import urllib2
# "urllib may help. DON'T TRY ALL NOTHINGS, since it will never end.
# 400 times is more than enough."

start = "http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=12345"
base ="http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing="

nothing = '12345'
nothing = '8022'
for x in xrange(400):
	url = base + nothing
	content = urllib2.urlopen(url).read()
	print content
	nothing = content.split(" ")[-1]