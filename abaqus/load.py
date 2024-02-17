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
#èâä˙í£óÕÇ∆ìôâøÇ»à≥óÕN/mm2
inip=503.5457381176929;#ã≠êßïœà ó (mm)
dx=0.02;

#------------------------------------------------------------------------------------
#ã´äEèåèÇÃê›íË
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[end1], r1[end2], )
region = regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].DisplacementBC(name='BC-1', createStepName='Initial', 
region=region, u1=SET, u2=SET, u3=SET, ur1=SET, ur2=SET, ur3=SET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p1], )
region = regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].DisplacementBC(name='BC-2', createStepName='Initial', 
region=region, u1=UNSET, u2=UNSET, u3=UNSET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)
mdb.models['Model-1'].boundaryConditions['BC-2'].setValuesInStep(
stepName='Step-2', ur3=dx, amplitude='main')



a = mdb.models['Model-1'].rootAssembly
f1 = a.instances['sp'].faces
faces1 = f1.getByBoundingBox(-1000,-30,-300,1000,-10,300,)
region = regionToolset.Region(faces=faces1)
mdb.models['Model-1'].DisplacementBC(name='BC-3', createStepName='Initial', 
region=region, u1=SET, u2=SET, u3=SET, ur1=SET, ur2=SET, ur3=SET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)


#É{ÉãÉgÇÃê›íË
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p1']
mdb.models['Model-1'].Pressure(name='Load-1', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p1')
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p2']
mdb.models['Model-1'].Pressure(name='Load-2', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p2')




