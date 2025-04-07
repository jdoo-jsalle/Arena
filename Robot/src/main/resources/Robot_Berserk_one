#1. PS
#2. AL
#3. PS
#4. TT si oui TR si non PS
#5. TT si oui TC si non PS
#6. TT si oui TR si non AL
#7. AL
#8. PS
#9. TT si oui TC si non TR
#10 AL

loop
	poursuite()
	avancer (Rand[2], Rand[2])
	poursuite()
	scan (4)
	if (detected == true)
	    tir (1,0)
	    tir (-1,0)
	else
	   poursuite ()
	endif
	scan (4)
	if (detected == true)
	    tir (0,1)
	    tir (0,-1)
	else
	   poursuite ()
	endif
	scan (4)
	if (detected == true)
	    tir (1,0)
	    tir (-1,0)
	else 
	  avancer (Rand[2], Rand[2])
	endif
	avancer (Rand[2], Rand[2])
	scan (4)
	if (detected == true)
	    tir (1,0)
	    tir (-1,0)
	else 
	    tir (0,1)
	    tir (0,-1)
	endif
	
	avancer (Rand[2], Rand[2])
endloop