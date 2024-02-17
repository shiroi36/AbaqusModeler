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
#入力事項
cpartname='endplate'
#断面ファイル
s0='endplate0'
#フランジ面スケッチファイル
s1='endplate1'

#シェル厚の設定
te=24.0

#スケッチの単なる読み込み
from dxf2abq import importdxf
importdxf(fileName='lib/'+s0+'.dxf')
importdxf(fileName='lib/'+s1+'.dxf')
#-------------------------------------------------------------------------------------------


s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.sketchOptions.setValues(gridOrigin=(120.0, 0.0))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['endplate0'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)

p = mdb.models['Model-1'].parts[cpartname]
p.BaseShell(sketch=s)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts[cpartname]
f, e, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=f[0], sketchUpEdge=e[13], 
sketchPlaneSide=SIDE1, origin=(0, 0.0, 0.0))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=1862.9, gridSpacing=46.57, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['endplate1'])
session.viewports['Viewport: 1'].view.fitView()

p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
p.PartitionFaceBySketch(sketchUpEdge=e1[13], faces=pickedFaces, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']




mdb.models['Model-1'].HomogeneousShellSection(name='ep', preIntegrate=OFF, 
material='sm490', thicknessType=UNIFORM, thickness=te, 
thicknessField='', idealization=NO_IDEALIZATION, 
poissonDefinition=DEFAULT, thicknessModulus=None, temperature=GRADIENT, 
useDensity=OFF, integrationRule=SIMPSON, numIntPts=5)


p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getSequenceFromMask(mask=('[#ffffff ]', ), )
region = regionToolset.Region(faces=faces)
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='ep', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedRegions = f.getSequenceFromMask(mask=('[#ffffff ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TRI, allowMapped=False)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=50.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#ffffff ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()


p = mdb.models['Model-1'].parts['endplate']
s = p.faces
side12Faces = s.getSequenceFromMask(mask=('[#fc0 ]', ), )
p.Surface(side12Faces=side12Faces, name='b1')

p = mdb.models['Model-1'].parts['endplate']
s = p.faces
side12Faces = s.getSequenceFromMask(mask=('[#3f ]', ), )
p.Surface(side12Faces=side12Faces, name='b2')

