import Image

img = Image.open("assets/cave.jpg")
width = img.size[0]
height = img.size[1]

img1 = Image.new(img.mode, (width, height))
img2 = Image.new(img.mode, (width, height))

for x in range(width):
    for y in range(height):
    	if x % 2 == y % 2:
    		img1.putpixel((x,y), img.getpixel((x,y)))
    	else:
    		img2.putpixel((x,y), img.getpixel((x,y)))

img1.save('img1.jpg')
img2.save('img2.jpg')