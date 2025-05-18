// Initialisation des variables
init
	affect(x, 1)
	affect(y, 1)
	affect(nd,0)
	affect(dep,0)
	affect(tor,1)
endinit
loop
   scan(4)
   if (detected== false)
      affect (nd,JS:nd+1)
   else 
     poursuite()
     tir (1,0)
     tir (0,1)
     tir (-1,0)
     tir (0,-1)
   endif
   if (nd > 3)
       if (dep < 5)
           avancer ($x,$y)
          // mine()
           affect (dep,JS:dep+1)
           if (block == true)
               if (tor == 0) 
                   affect (x,JS:x*-1)
                   affect (tor,1)
               else 
                   affect (y,JS:y*-1)
                   affect (tor,0)
               endif
           endif
       endif
       if (dep >=5)
          affect (dep,0)
          affect (nd,0)
       endif
   endif
endloop
  