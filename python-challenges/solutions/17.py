import bz2
import urllib
import urllib2
import xmlrpclib

# start = "http://www.pythonchallenge.com/pc/def/linkedlist.php?busynothing=45439"
base ="http://www.pythonchallenge.com/pc/def/linkedlist.php?busynothing="
vis = {}
nothing = '12345'
headers = ""

for x in xrange(10000):
	if nothing in vis:
		break
	vis[nothing] = True
	url = base + nothing
	request = urllib2.Request(url)
	response = urllib2.urlopen(request)

	cookie_val = response.headers.getheader('set-cookie')
	headers += cookie_val[cookie_val.index('=')+1:cookie_val.index(';')]
	content = response.read()

	if x % 30 == 0:
		print content
	# print headers
	nothing = content.split(" ")[-1]

print 'Done!!!'
print headers

# headers = BZh91AY%26SY%94%3A%E2I%00%00%21%19%80P%81%11%00%AFg%9E%A0+%00hE%3DM%B5%23%D0%D4%D1%E2%8D%06%A9%FA%26S%D4%D3%21%A1%EAi7h%9B%9A%2B%BF%60%22%C5WX%E1%ADL%80%E8V%3C%C6%A8%DBH%2632%18%A8x%01%08%21%8DS%0B%C8%AF%96KO%CA2%B0%F1%BD%1Du%A0%86%05%92s%B0%92%C4Bc%F1w%24S%85%09%09C%AE%24%90
dec = 'BZh91AY%26SY%94%3A%E2I%00%00%21%19%80P%81%11%00%AFg%9E%A0+%00hE%3DM%B5%23%D0%D4%D1%E2%8D%06%A9%FA%26S%D4%D3%21%A1%EAi7h%9B%9A%2B%BF%60%22%C5WX%E1%ADL%80%E8V%3C%C6%A8%DBH%2632%18%A8x%01%08%21%8DS%0B%C8%AF%96KO%CA2%B0%F1%BD%1Du%A0%86%05%92s%B0%92%C4Bc%F1w%24S%85%09%09C%AE%24%90'
dec = urllib.unquote_plus(dec) # or urllib2.unquote(), but dec[i] changes from a space to a '+'

print bz2.decompress(dec) # is it the 26th already? call his father and inform him that "the flowers are on their way". he'll understand.

conn = xmlrpclib.ServerProxy("http://www.pythonchallenge.com/pc/phonebook.php")

print conn.phone('Leopold') # 555-VIOLIN

req = urllib2.Request("http://www.pythonchallenge.com/pc/stuff/violin.php")
req.add_header("Cookie", "info=the flowers are on their way")
resp = urllib2.urlopen(req)
print resp.read()
