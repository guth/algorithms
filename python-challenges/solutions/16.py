import Image
PINK = 195 # Determined via image editor

url = 'assets/mozart.gif'
im = Image.open(url)
width = im.size[0]
height = im.size[1]

# Each row has one 5-pixel wide pink streak.
# The code below finds those streaks and swaps the left part with the right part
for y in range(im.size[1]):
	row = [im.getpixel((x,y)) for x in xrange(width)]
	a = 0
	for p in row:
		if p == 195:
			break;
		a += 1
	b = a+4

	for x in range(im.size[0]):
		a,b = -1, -1
		if im.getpixel((x,y)) == PINK:
			a = x
			b = x+4
			for dx in range(im.size[0]-b):
				im.putpixel((a+dx, y), im.getpixel((b+dx,y))) # remove the pink, shift everything left
		if a != -1 and b != -1:
			break
	row = row[b:] + row[:b]

	for x in range(len(row)):
		im.putpixel((x,y), row[x])
im.save('mozart.gif')