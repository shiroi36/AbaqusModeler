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



#-------------------------------------------------------------------------------------------
#ì¸óÕéñçÄ
cpartname='sp600_M24'
#SPLå˙ÇÃê›íË
ts=12.0



#--------------------------------------
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
p = mdb.models['Model-1'].parts[cpartname]
p.features['Solid extrude-1'].setValues(depth=ts)
p = mdb.models['Model-1'].parts[cpartname]
p.regenerate()
p = mdb.models['Model-1'].parts[cpartname]
p.regenerate()
session.viewports['Viewport: 1'].partDisplay.setValues(sectionAssignments=OFF, engineeringFeatures=OFF, mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(meshTechnique=ON)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()
