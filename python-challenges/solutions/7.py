import Image

im = Image.open("assets/oxygen.png")
bbox = im.getbbox() # (0, 0, 629, 95)
width = bbox[2]
height = bbox[3]
# Gray area: (0, 43) to (607, 51)
y = 0
while y < height:
	pixel = im.getpixel((0, y))
	if pixel[0] == pixel[1] and pixel[1] == pixel[2]:
		break
	y += 1

x0 = 0
x1 = 0

while x1 < width:
	pixel = im.getpixel((x1, y))
	if pixel[0] != pixel[1] or pixel[1] != pixel[2]:
		break
	x1 += 1

x1 -= 1
print x0, y
print x1, y

l = []
# Gray area runs from x0 to x1 along the horizontal line at value y
for x in xrange(x0+1, x1):
	pl = im.getpixel((x-1, y)) # pixel left
	pr = im.getpixel((x, y)) # pixel right
	if pl[0] != pr[0] or pl[1] != pr[1] or pl[2] != pr[2]:
		l.append(pl)
l.append(im.getpixel((x1,y)))

print l
ans = ""
for p in l:
	ans += chr(p[0])
print ans

nextLevel = [105, 110, 116, 101, 103, 114, 105, 116, 121]
ans = ""
for p in nextLevel:
	ans += chr(p)
print ans
