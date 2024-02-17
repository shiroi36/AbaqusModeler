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



lw=266.0

s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.Line(point1=(0.0, 0.0), point2=(9.0, 0.0))
s.HorizontalConstraint(entity=g[2], addUndoState=False)
s.Line(point1=(9.0, 0.0), point2=(9.0, 9.0))
s.VerticalConstraint(entity=g[3], addUndoState=False)
s.PerpendicularConstraint(entity1=g[2], entity2=g[3], addUndoState=False)
s.Line(point1=(9.0, 9.0), point2=(0.0, 0.0))
p = mdb.models['Model-1'].Part(name='weld1', dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts['weld1']
p.BaseSolidExtrude(sketch=s, depth=lw)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts['weld1']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
session.viewports['Viewport: 1'].partDisplay.setValues(mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=ON)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=OFF)
p = mdb.models['Model-1'].parts['weld1']
p.seedPart(size=25.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['weld1']
p.generateMesh()
session.viewports['Viewport: 1'].view.setValues(nearPlane=400.545, 
farPlane=600.75, cameraPosition=(293.549, 293.549, 414.049), 
cameraUpVector=(-0.547381, 0.576592, -0.606561))
session.viewports['Viewport: 1'].partDisplay.setValues(sectionAssignments=ON, 
engineeringFeatures=ON, mesh=OFF)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=OFF)
p = mdb.models['Model-1'].parts['weld1']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1 ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts['weld1']
p.SectionAssignment(region=region, sectionName='sm490', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


p = mdb.models['Model-1'].parts['weld1']
p.deleteMesh()
p = mdb.models['Model-1'].parts['weld1']
p.seedPart(size=20.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['weld1']
p.generateMesh()
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(-271.5, 
72.0747, 537.196), cameraUpVector=(0.118636, 0.875242, -0.468911), 
cameraTarget=(4.50005, 4.50004, 125))
p = mdb.models['Model-1'].parts['weld1']
p.generateMesh()
session.viewports['Viewport: 1'].view.setValues(nearPlane=355.531, 
farPlane=645.764, cameraPosition=(118.24, 108.635, 601.306), 
cameraUpVector=(-0.173607, 0.851348, -0.495042), cameraTarget=(4.50001, 
4.50004, 125))




