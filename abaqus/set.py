# -*- coding: mbcs -*-
# Do not delete the following import lines
from abaqus import *
from abaqusConstants import *
import __main__

import section
import regionToolset
import displayGroupMdbToolset as dgm
import part
import material
import assembly
import step
import interaction
import load
import mesh
import optimization
import job
import sketch
import visualization
import xyPlot
import displayGroupOdbToolset as dgo
import connectorBehavior
import os



#------------------------------------------------------------------------------------
#ì¸óÕèÓïÒ
p1=int(160)
end1=int(161)
end2=int(162)




a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p1], r1[end1], r1[end2], )
a.Set(referencePoints=refPoints1, name='outputnode_rf')

a = mdb.models['Model-1'].rootAssembly
f1 = a.instances['b'].faces
faces1 = f1.getByBoundingBox(-1000,-2,-1000,1000,1000,1000)
c1 = a.instances['kpl-u'].cells
cells1 = c1.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c2 = a.instances['kpr-u'].cells
cells2 = c2.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c3 = a.instances['kpl-d'].cells
cells3 = c3.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c4 = a.instances['kpr-d'].cells
cells4 = c4.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c5 = a.instances['sp'].cells
cells5 = c5.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
a.Set(faces=faces1,cells=cells1+cells2+cells3+cells4+cells5, name='outputnode_u')

a = mdb.models['Model-1'].rootAssembly
c1 = a.instances['kpl-u'].cells
cells1 = c1.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c2 = a.instances['kpr-u'].cells
cells2 = c2.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c3 = a.instances['kpl-d'].cells
cells3 = c3.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c4 = a.instances['kpr-d'].cells
cells4 = c4.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
c5 = a.instances['sp'].cells
cells5 = c5.getByBoundingBox(-1000,-50,-1000,1000,2,1000)
a.Set(cells=cells1+cells2+cells3+cells4+cells5, name='outputelement')


