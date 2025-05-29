// Initialisation des variables
init
	affect(x, 0)
	affect(y, 0)
	affect(z, 1)
endinit
loop

   if (x < 10) 
      avancer (1,0)
      mine ()
      affect (x,JS:x+1)
   else
      if (y < 10)
         avancer (0,1)
         affect (y,JS:y+1)
      else
         avancer ($z,0)
         affect (z,JS:z*-1)
      endif
   endif
   rotate (1)
endloop
    
 
  