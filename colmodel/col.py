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
cpartname='col'
lc=2000.0;
h=500.0;
bc=300.0;
#断面ファイル
s0='c00'
#フランジ面スケッチファイル
s1='c01'
#フランジ面スケッチファイル
s2='c02'
#ウェブ面スケッチファイル
s3='c03'
#ウェブ面スケッチファイル
s4='c04'

#材料
mat='sm490';

#シェル厚の設定
tw=16.0
tcwr=12.0
tf=22.0
tcfr=12.0
#スケッチの単なる読み込み
from dxf2abq import importdxf
importdxf(fileName='lib/'+s0+'.dxf')
importdxf(fileName='lib/'+s1+'.dxf')
importdxf(fileName='lib/'+s2+'.dxf')
importdxf(fileName='lib/'+s3+'.dxf')
importdxf(fileName='lib/'+s4+'.dxf')
#-------------------------------------------------------------------------------------------
#パートモジュール
#スケッチの単なる押し出し。ここではtest00スケッチ3000mm押し出している。

s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['c00'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s, depth=lc)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']




p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[1], normal=e[4], cells=pickedCells)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[32], normal=e1[43], 
cells=pickedCells)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8 ]', ), )
e, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[40], normal=e[62], 
cells=pickedCells)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10 ]', ), )
e1, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[4], normal=e1[4], cells=pickedCells)




p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v[5], normal=e[18], cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[25], normal=e1[25], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#88 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v[21], normal=e[67], cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#220 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[30], normal=e1[59], 
cells=pickedCells)



#-------------------------------------------------------------------------------------------
#データム軸の作成
p = mdb.models['Model-1'].parts['col']
p.deleteFeatures(('Datum axis-3', 'Datum axis-2', 'Datum axis-1', ))
p = mdb.models['Model-1'].parts['col']
p.DatumAxisByPrincipalAxis(principalAxis=XAXIS)
p = mdb.models['Model-1'].parts['col']
p.DatumAxisByPrincipalAxis(principalAxis=YAXIS)
p = mdb.models['Model-1'].parts['col']
p.DatumAxisByPrincipalAxis(principalAxis=ZAXIS)

p = mdb.models['Model-1'].parts['col']
p.DatumPlaneByPrincipalPlane(principalPlane=XZPLANE, offset=h/2)
p = mdb.models['Model-1'].parts['col']
p.DatumPlaneByPrincipalPlane(principalPlane=YZPLANE, offset=bc/2)





#-------------------------------------------------------------------------------------------
#フランジ切り取り c01
p = mdb.models['Model-1'].parts['col']
e, d = p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d[13], sketchUpEdge=e[102], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, 250.0, 
1000.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4999.83, gridSpacing=124.99, transform=t)
g, v, d1, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['col']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['c01'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts['col']
e1, d2 = p.edges, p.datums
p.CutExtrude(sketchPlane=d2[13], sketchUpEdge=e1[102], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, flipExtrudeDirection=OFF)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']





#-------------------------------------------------------------------------------------------
#ウェブ孔あけ
p = mdb.models['Model-1'].parts['col']
e, d1 = p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[14], sketchUpEdge=e[266], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(150.0, 0.0, 
1000.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4999.83, gridSpacing=124.99, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['col']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['c03'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts['col']
e1, d2 = p.edges, p.datums
p.CutExtrude(sketchPlane=d2[14], sketchUpEdge=e1[266], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, flipExtrudeDirection=OFF)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']

#-------------------------------------------------------------------------------------------
#ウェブスケッチ

p = mdb.models['Model-1'].parts['col']
f, e, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[14], sketchUpEdge=e[322], 
sketchPlaneSide=SIDE1, origin=(150.0, 0.0, 1000.0))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4999.83, gridSpacing=124.99, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['col']
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['c04'])

p = mdb.models['Model-1'].parts['col']
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#ffffffff:5 #3ffff ]', ), )
f1, e1, d2 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d2[14], sketchUpEdge=e1[322], 
faces=pickedFaces, sketchPlaneSide=SIDE1, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']

#-------------------------------------------------------------------------------------------
#ウェブパーテーション

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[137], normal=e1[184], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#18f2c341 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[230], normal=e[185], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#58001e10 #31a ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[266], normal=e1[420], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1c201c4 #614800 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[92], normal=e[489], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3880e80 #90000000 #c2 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[414], normal=e1[575], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#15bbb #10 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[463], normal=e[803], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2a156270 #20000 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[94], normal=e1[131], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#c13f9 #500 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[153], normal=e[82], 
cells=pickedCells)
session.viewports['Viewport: 1'].view.setValues(nearPlane=4639.61, 
farPlane=5360.07, width=492.003, height=329.736, cameraPosition=(
19.596, -4999.84, 621.427), cameraTarget=(19.596, 0, 621.427))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=(
'[#800c000a #a000001 #c00028 #a030 #3 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[91], normal=e1[149], 
cells=pickedCells)
session.viewports['Viewport: 1'].view.setValues(nearPlane=4616.76, 
farPlane=5382.92, width=678.653, height=454.827, cameraPosition=(
210.895, -4999.84, 642.805), cameraTarget=(210.895, 0, 642.805))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=(
'[#19000000 #4880040 #10800801 #2300208 #50000 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[193], normal=e[311], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#0 #4180c000 #85588 #800 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[681], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[685], rule=MIDDLE))

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1ed #18000000 #800030 #1 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[426], normal=e[799], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#0:4 #1000f1d8 #101800 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[1106], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[1118], rule=MIDDLE))

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2e0 #0:3 #102b0000 #3000200 #2 ]', 
), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[632], normal=e[1249], 
cells=pickedCells)



#-------------------------------------------------------------------------------------------
#フランジパーテーション
p = mdb.models['Model-1'].parts['col']
f1, e, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[13], sketchUpEdge=e[1468], 
sketchPlaneSide=SIDE1, origin=(0.0, 250.0, 1000.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4999.83, gridSpacing=124.99, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['col']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['c02'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts['col']
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=(
'[#ffffffff:9 #f7ffffff #fbffffff #ffffffff:21 #fbf7ffff #ffef67fd #7f ]', 
), )
f, e1, d2 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d2[13], sketchUpEdge=e1[1468], 
faces=pickedFaces, sketchPlaneSide=SIDE1, sketch=s)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']
session.viewports['Viewport: 1'].view.setValues(session.views['Left'])
session.viewports['Viewport: 1'].view.setValues(nearPlane=4153.38, 
farPlane=5846.29, cameraPosition=(-4193.3, 2507.01, -62.8127), 
cameraUpVector=(0.479716, 0.864932, 0.147533))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4342.09, 
farPlane=5657.6, width=827.374, height=554.499, cameraPosition=(
-4084.66, 2574.54, -332.171), cameraTarget=(108.644, 67.5308, 730.641))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4521.69, 
farPlane=5320.9, cameraPosition=(-4819.39, 842.425, 395.294), 
cameraUpVector=(0.122984, 0.930937, 0.343847), cameraTarget=(108.644, 
67.5307, 730.641))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4387.57, 
farPlane=5455.01, width=2120.09, height=1420.86, cameraPosition=(
-4821.86, 832.03, 407.534), cameraTarget=(106.177, 57.1361, 742.881))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4301.03, 
farPlane=5647.95, cameraPosition=(-3118.53, 3836.67, 390.143), 
cameraUpVector=(0.653452, 0.589677, 0.474638), cameraTarget=(78.989, 
9.17742, 743.158))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4448.56, 
farPlane=5500.42, width=787.771, height=527.957, cameraPosition=(
-3164.53, 3790.23, 303.284), cameraTarget=(32.9869, -37.2642, 656.3))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4072.26, 
farPlane=6253.58, cameraPosition=(-2362.55, 3164.33, -2338.5), 
cameraUpVector=(0.591054, 0.741218, 0.3182), cameraTarget=(28.8992, 
-34.074, 669.765))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4092.13, 
farPlane=6233.71, width=578.148, height=387.47, cameraPosition=(
-2296.13, 3203.88, -2349.25), cameraTarget=(95.3229, 5.47951, 659.014))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#0 #c0000000 #d87ac000 #702 ]', ), 
)
e, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[7], normal=e[15], cells=pickedCells)
session.viewports['Viewport: 1'].view.setValues(nearPlane=3800.29, 
farPlane=6525.55, width=3352.34, height=2246.71, cameraPosition=(
-2694.13, 3296.81, -1934.05), cameraTarget=(-302.682, 98.4066, 
1074.21))
session.viewports['Viewport: 1'].view.setValues(session.views['Front'])
session.viewports['Viewport: 1'].view.setValues(session.views['Left'])
session.viewports['Viewport: 1'].view.setValues(nearPlane=3939.48, 
farPlane=6060.2, cameraPosition=(-3855.46, 2464.12, -1015.44), 
cameraUpVector=(0.470503, 0.867635, 0.160735))
session.viewports['Viewport: 1'].view.setValues(nearPlane=4163.79, 
farPlane=5835.9, width=536.534, height=359.58, cameraPosition=(
-3898.63, 2770.86, -557.83), cameraTarget=(-43.1734, 306.736, 1457.61))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#0:2 #1800a0 #0:3 #d3c1400 #27 ]', 
), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[117], normal=e1[199], 
cells=pickedCells)

p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=(
'[#100400 #8004 #300 #0 #1000c #40000080 #4002', ' #10008 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[730], normal=e1[1434], 
cells=pickedCells)

#-------------------------------------------------------------------------------------------
#ウェブパーテーション
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#201 #40000000 #0:4 #10000 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[50], normal=e[68], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2010 #0 #4 #0:3 #100000 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[65], normal=e1[89], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#c #0 #40 #0:3 #1000000 ]', ), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[62], normal=e[89], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#808000 #0:2 #10 #0:4 #800 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[550], normal=e1[985], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8080000 #0:2 #100 #0:4 #8000 ]', 
), )
e, v2, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[51], normal=e[52], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80800003 ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[50], normal=e1[54], 
cells=pickedCells)


#-------------------------------------------------------------------------------------------
#材料


p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:8 #3fffffff ]', ), )
region = p.Set(cells=cells, name='Set-14')
p = mdb.models['Model-1'].parts['col']
p.SectionAssignment(region=region, sectionName='ss400', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


#-------------------------------------------------------------------------------------------
#パーテーション


p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:3 #8000000 #40 #420 #4080 #0:33 #70000000', ' #3800 ]', ), )
p.Surface(side1Faces=side1Faces, name='c0')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#40200000 #20000 #200010 #100 #0:36 #e000000 #1c000 ]', ), )
p.Surface(side1Faces=side1Faces, name='c1')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:4 #402 #4002 #100002 #0:34 #fc ]', ), )
p.Surface(side1Faces=side1Faces, name='c2')
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(-4354.16, 
-195.507, -1455.65), cameraTarget=(-169.02, -74.5048, 1277.2))
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#4008000 #40200000 #8000000 #4 #0:36 #80000000 #703 ]', ), )
p.Surface(side1Faces=side1Faces, name='c3')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#1000 #8008004 #82000000 #40400000 #12000000 #40000000 #80000', 
' #0:9 #c0000 #0 #28000 #0:10 #220000 ]', ), )
p.Surface(side1Faces=side1Faces, name='c4')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#20200 #4000000 #80001 #1000040 #8000 #40010 #2008', 
' #0:5 #40000000 #1 #0 #440 #0:17 #2800', ' #0 #120 #0:3 #18000000 ]', 
), )
p.Surface(side1Faces=side1Faces, name='c5')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#1000 #8008004 #82000000 #40400000 #12000000 #40000000 #80000', 
' #0:9 #c0000 #0 #28000 #0:5 #1100 #0:2', ' #4200000 #0 #220000 ]', ), 
)
p.Surface(side1Faces=side1Faces, name='c4')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#100100 #800000 #40008 #4000020 #1000 #20001 #840', 
' #0:5 #a0000000 #0:2 #a00 #0:15 #82000000 #0:3', ' #280 #0 #44 ]', ), 
)
p.Surface(side1Faces=side1Faces, name='c6')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#4000 #10002002 #1000000 #80100002 #41000000 #80000000 #10000', 
' #0:10 #c000 #14000 #0:7 #500 #110000 #0:12', ' #1000000 #40000 ]', ), 
)
p.Surface(side1Faces=side1Faces, name='c7')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:10 #1014000 #0:2 #204000 #408000 #0:5 #10010', 
' #0 #290000 #0:9 #30900 #0 #1000001 #0', ' #40000 #40000000 ]', ), )
p.Surface(side1Faces=side1Faces, name='c8')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:8 #84000000 #4 #0:6 #200500 #0 #40000000', 
' #4010 #2204 #0 #85000000 #800001 #0:2 #600000', 
' #40 #0:2 #1100 #0:6 #90000000 #0:8 #10 ]', ), )
p.Surface(side1Faces=side1Faces, name='c9')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:10 #1014000 #0 #20000 #204000 #408000 #40000', 
' #0:4 #10010 #0 #290000 #0:9 #80030900 #0', 
' #1000001 #100000 #48000 #40000000 ]', ), )
p.Surface(side1Faces=side1Faces, name='c8')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:10 #20000000 #40 #0 #20402000 #10804000 #0:5', 
' #25000000 #80000000 #100 #0:8 #288 #0 #11000000', 
' #8040004 #0:2 #140000 #10000000 #1 ]', ), )
p.Surface(side1Faces=side1Faces, name='c10')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:9 #4080 #0:6 #2004000 #40000 #4 #401', 
' #80000000 #4000094 #21 #0 #80000000 #10080000 #0', 
' #2000000 #80000082 #8 #0:14 #400 #10000 ]', ), )
p.Surface(side1Faces=side1Faces, name='c11')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:20 #a0000000 #0:8 #8 #0 #80 #0', 
' #1000000 #0:10 #400 ]', ), )
p.Surface(side1Faces=side1Faces, name='c12')
p = mdb.models['Model-1'].parts['col']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:20 #12000 #0:11 #20800 #0:4 #80000000 #0:8', ' #10 ]', ), )
p.Surface(side1Faces=side1Faces, name='c13')






#-------------------------------------------------------------------------------------------
#メッシュ
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#f6000000 #fcf7a7f5 #7e39f3f9 #f93ef39b #95d79dc7 #8ec3bff0 #d551caa6', 
' #92e7caa5 #2f631f50 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=HEX, technique=STRUCTURED)
elemType1 = mesh.ElemType(elemCode=C3D8R)
elemType2 = mesh.ElemType(elemCode=C3D6)
elemType3 = mesh.ElemType(elemCode=C3D4)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=(
'[#f6000000 #fcf7a7f5 #7e39f3f9 #f93ef39b #95d79dc7 #8ec3bff0 #d551caa6', 
' #92e7caa5 #2f631f50 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#0 #3085800 #81860c06 #6c00c64 #60000000 #303c0003 #2203119', 
' #d083458 #101ce004 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=(
'[#0 #3085800 #81860c06 #6c00c64 #60000000 #303c0003 #2203119', 
' #d083458 #101ce004 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#9ffffff #a #400000 #10000 #0:3 #100 #800000 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=(
'[#9ffffff #a #400000 #10000 #0:3 #100 #800000 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#0:4 #6018 #0 #28800000 #60100002 #ab ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:4 #6018 #0 #28800000 #60100002 #ab ]', 
), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#0:4 #a280220 #4100400c #e0440 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:4 #a280220 #4100400c #e0440 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))


elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts['col']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:8 #3fffffff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))


p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#8559aa6 #2 #0 #10000 #0:3 #100 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['col']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0 #5000000 #e0070001 #253 #e940000 #e000b400 #0', 
' #a038f000 #e002 #14000000 #253e0004 #700 #0 #dc01285a', 
' #1 #5800000 #37180e00 #0:21 #80000000 #0 #800000', 
' #0 #400 #0:11 #1000000 #0:12 #77fc ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)
p = mdb.models['Model-1'].parts['col']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#1aa6559 #8 #400000 #0:5 #800000 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['col']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0 #11400 #700874e #0 #70000000 #13a600da #0', 
' #3800500 #47c #0 #10500 #869e007 #0 #27e0000', 
' #69e #70000000 #c004809c #1 #0:13 #10000 #0:3', 
' #24000000 #0:29 #1000000 #ff800000 #803 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)

p = mdb.models['Model-1'].parts['col']
p.seedPart(size=20.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['col']
p.generateMesh()


p = mdb.models['Model-1'].parts['col']
f = p.faces
faces = f.getSequenceFromMask(mask=(
'[#0:19 #20000000 #0 #800008 #20000000 #0:15 #100', 
' #0:5 #22480000 #c60492 #400 ]', ), )
p.Set(faces=faces, name='e0')

p = mdb.models['Model-1'].parts['col']
f = p.faces
faces = f.getSequenceFromMask(mask=(
'[#0:20 #100008 #40000000 #2000 #0:15 #4000 #0:5', 
' #14220000 #300c249 #200 ]', ), )
p.Set(faces=faces, name='e1')


