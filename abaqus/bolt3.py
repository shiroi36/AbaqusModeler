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
cpartname='bolt3'
d1=20.0;
t1=14.0
d2=11.0;
t2=24.0;


s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.CircleByCenterPerimeter(center=(0.0, 0.0), point1=(d1, 0.0))
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s, depth=t1)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']


p = mdb.models['Model-1'].parts[cpartname]
f, e = p.faces, p.edges
t = p.MakeSketchTransform(sketchPlane=f[1], sketchUpEdge=e[0], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, 0.0, 
20.0))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=229.17, gridSpacing=5.72, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.CircleByCenterPerimeter(center=(0.0, 0.0), point1=(d2, 0.0))
p = mdb.models['Model-1'].parts[cpartname]
f1, e1 = p.faces, p.edges
p.SolidExtrude(sketchPlane=f1[1], sketchUpEdge=e1[0], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s1, depth=t2, 
flipExtrudeDirection=OFF)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


p = mdb.models['Model-1'].parts[cpartname]
f, e = p.faces, p.edges
t = p.MakeSketchTransform(sketchPlane=f[1], sketchUpEdge=e[0], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, 0.0, 
120.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=301.52, gridSpacing=7.53, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.CircleByCenterPerimeter(center=(0.0, 0.0), point1=(d1, 0.0))
p = mdb.models['Model-1'].parts[cpartname]
f1, e1 = p.faces, p.edges
p.SolidExtrude(sketchPlane=f1[1], sketchUpEdge=e1[0], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, depth=t1, 
flipExtrudeDirection=OFF)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
p.DatumAxisByTwoPoint(point1=p.InterestingPoint(edge=e[2], rule=CENTER), 
point2=p.InterestingPoint(edge=e[5], rule=CENTER))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[3], normal=d1[4], cells=pickedCells)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[2], normal=d2[4], cells=pickedCells)
p = mdb.models['Model-1'].parts[cpartname]
v1, e1 = p.vertices, p.edges
p.DatumAxisByTwoPoint(point2=v1[5], point1=p.InterestingPoint(edge=e1[5], 
rule=MIDDLE))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#7 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=d1[7], cells=pickedCells, 
point=p.InterestingPoint(edge=e[5], rule=CENTER))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3f ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[3], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[14], rule=CENTER))



p = mdb.models['Model-1'].parts[cpartname]
p.DatumPointByCoordinate(coords=(0.0, 0.0, 0.0))

#çﬁóøÇÃÇÌÇËÇ†Çƒ

p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#fff ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='F14T', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)

#ÉÅÉbÉVÉÖÇÃÇÌÇËÇ†Çƒ

p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#cf3 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#cf3 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=20.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=15.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=10.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=15.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()


elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#fff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, elemType3))

