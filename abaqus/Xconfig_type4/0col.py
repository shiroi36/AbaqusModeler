
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
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, h/2, 
lc/2))
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
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(bc/2, 0.0, 
lc/2))
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

mdb.models['Model-1'].ConstrainedSketch(gridSpacing=124.18, name='__profile__', 
    sheetSize=4967.24, transform=
    mdb.models['Model-1'].parts['col'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['col'].datums[14], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['col'].edges[38], 
    sketchOrientation=RIGHT, origin=(bc/2, 0.0, lc/2)))
mdb.models['Model-1'].parts['col'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
del mdb.models['Model-1'].sketches['__profile__']
mdb.models['Model-1'].ConstrainedSketch(gridSpacing=124.18, name='__profile__', 
    sheetSize=4967.24, transform=
    mdb.models['Model-1'].parts['col'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['col'].datums[14], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['col'].edges[306], 
    sketchOrientation=RIGHT, origin=(bc/2, 0.0, lc/2)))
mdb.models['Model-1'].parts['col'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['c04'])
mdb.models['Model-1'].parts['col'].PartitionFaceBySketchThruAll(faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#ffffffff:3 #e6cbffff #fe9cbd92 #1ff ]', ), ), sketch=
    mdb.models['Model-1'].sketches['__profile__'], sketchPlane=
    mdb.models['Model-1'].parts['col'].datums[14], sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['col'].edges[306])
del mdb.models['Model-1'].sketches['__profile__']




#-------------------------------------------------------------------------------------------
#ウェブパーテーション

mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#40 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['col'].edges[269], ), line=
    mdb.models['Model-1'].parts['col'].edges[34], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#80 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['col'].edges[283], ), line=
    mdb.models['Model-1'].parts['col'].edges[24], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#100 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['col'].edges[282], ), line=
    mdb.models['Model-1'].parts['col'].edges[26], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#200 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['col'].edges[273], ), line=
    mdb.models['Model-1'].parts['col'].edges[28], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#400 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['col'].edges[280], ), line=
    mdb.models['Model-1'].parts['col'].edges[30], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#1 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[275], ), line=
    mdb.models['Model-1'].parts['col'].edges[32], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#2 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[279], ), line=
    mdb.models['Model-1'].parts['col'].edges[34], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#1 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[278], ), line=
    mdb.models['Model-1'].parts['col'].edges[36], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#1 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[269], ), line=
    mdb.models['Model-1'].parts['col'].edges[38], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#2 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[271], ), line=
    mdb.models['Model-1'].parts['col'].edges[40], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#4 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[291], ), line=
    mdb.models['Model-1'].parts['col'].edges[42], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#8 ]', ), )
    , edges=(mdb.models['Model-1'].parts['col'].edges[290], ), line=
    mdb.models['Model-1'].parts['col'].edges[44], sense=REVERSE)
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#1fbf010 ]', ), ), normal=mdb.models['Model-1'].parts['col'].edges[292], 
    point=mdb.models['Model-1'].parts['col'].vertices[232])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#21000 ]', 
    ), ), normal=mdb.models['Model-1'].parts['col'].edges[33], point=
    mdb.models['Model-1'].parts['col'].vertices[25])



#-------------------------------------------------------------------------------------------
#フランジスケッチ

mdb.models['Model-1'].ConstrainedSketch(gridSpacing=124.18, name='__profile__', 
    sheetSize=4967.24, transform=
    mdb.models['Model-1'].parts['col'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['col'].datums[13], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['col'].edges[94], 
    sketchOrientation=RIGHT, origin=(0.0, 220.0, lc/2)))
mdb.models['Model-1'].parts['col'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['c02'])
mdb.models['Model-1'].parts['col'].PartitionFaceBySketchThruAll(faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#fffffffb #ffff7fff #ffffffff:4 #2bffffff #72724993 #3fff8 ]', ), ), 
    sketch=mdb.models['Model-1'].sketches['__profile__'], sketchPlane=
    mdb.models['Model-1'].parts['col'].datums[13], sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['col'].edges[94])
del mdb.models['Model-1'].sketches['__profile__']
# Save by 14analysis on 2018_10_03-18.30.35; build 6.12-1 2012_03_13-20.23.18 119612



#-------------------------------------------------------------------------------------------
#フランジパーテーション

mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#b0082519 #9c ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[291], point=
    mdb.models['Model-1'].parts['col'].vertices[192])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#16b98000 #185200 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[191], point=
    mdb.models['Model-1'].parts['col'].vertices[122])
# Save by 14analysis on 2018_10_03-18.36.44; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#8400 #80000002 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[13], point=
    mdb.models['Model-1'].parts['col'].vertices[338])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#400000 #42 #200 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[160], point=
    mdb.models['Model-1'].parts['col'].vertices[375])
# Save by 14analysis on 2018_10_09-16.09.33; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#2609840b #5a24000 #1080 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[140], point=
    mdb.models['Model-1'].parts['col'].vertices[167])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#1807040 #88012 #20016810 #84 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[391], point=
    mdb.models['Model-1'].parts['col'].vertices[252])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #2360000 #8ab8c00 #4800021 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[539], point=
    mdb.models['Model-1'].parts['col'].vertices[310])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#202e25 #0 #4a40 #420202 #22000 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[210], point=
    mdb.models['Model-1'].parts['col'].vertices[387])
# Save by 14analysis on 2018_10_09-16.32.55; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #20100000 #10100 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[470], point=
    mdb.models['Model-1'].parts['col'].vertices[270])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#2000 #8080 #0 #8000000 ]', ), ), normal=
    mdb.models['Model-1'].parts['col'].edges[1016], point=
    mdb.models['Model-1'].parts['col'].vertices[171])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#f0 ]', ), 
    ), normal=mdb.models['Model-1'].parts['col'].edges[535], point=
    mdb.models['Model-1'].parts['col'].vertices[304])
mdb.models['Model-1'].parts['col'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(('[#200070 ]', 
    ), ), normal=mdb.models['Model-1'].parts['col'].edges[64], point=
    mdb.models['Model-1'].parts['col'].vertices[52])
# Save by 14analysis on 2018_10_09-17.20.10; build 6.12-1 2012_03_13-20.23.18 119612



#-------------------------------------------------------------------------------------------
#材料

mdb.models['Model-1'].parts['col'].SectionAssignment(offset=0.0, offsetField=''
    , offsetType=MIDDLE_SURFACE, region=Region(
    cells=mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask(mask=(
    '[#ffffffff:5 #fffffff ]', ), )), sectionName='ss400flat', 
    thicknessAssignment=FROM_SECTION)
# Save by 14analysis on 2018_10_09-17.42.53; build 6.12-1 2012_03_13-20.23.18 119612


#-------------------------------------------------------------------------------------------
#メッシュ
mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#3c0000 #c00347f3 #e0ed804e #dd000000 #f885de03 #45f8003 ]', ), ), 
    technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#3c0000 #c00347f3 #e0ed804e #dd000000 #f885de03 #45f8003 ]', ), ), ))
mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #8800 #0:2 #20 #207f7c ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #8800 #0:2 #20 #207f7c ]', ), ), ))
mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#3d810000 #6901000 #0 #210000 #20000 #800000 ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#3d810000 #6901000 #0 #210000 #20000 #800000 ]', ), ), ))
mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #10000000 #18000b80 #80cce #0 #2000000 ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0 #10000000 #18000b80 #80cce #0 #2000000 ]', ), ), ))
mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#20000 #21202000 #1400 #54d110 #4 ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#20000 #21202000 #1400 #54d110 #4 ]', ), ), ))

mdb.models['Model-1'].parts['col'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0:3 #f6000000 #4d354719 #d070080 ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#0:3 #f6000000 #4d354719 #d070080 ]', ), ), ))
# Save by 14analysis on 2018_10_09-17.47.58; build 6.12-1 2012_03_13-20.23.18 119612


mdb.models['Model-1'].parts['col'].seedPart(deviationFactor=0.1, minSizeFactor=
    0.1, size=30.0)
mdb.models['Model-1'].parts['col'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:9 #7800 #f #0:9 #f0078 #0:9 #c7800000', 
    ' #3 #79e00000 #0 #fff80000 #7ffff ]'), ), number=8)
mdb.models['Model-1'].parts['col'].seedEdgeByBias(biasMethod=SINGLE, 
    constraint=FINER, end1Edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:30 #120000 #54090004 #210b451 #2228da #20100 #0', 
    ' #10000000 #400000 ]'), ), end2Edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:29 #68000000 #800552b #1006a50 #20000 #1100001 #8489', 
    ' #0 #2000000 #100000 ]'), ), maxSize=200.0, minSize=30.0)
mdb.models['Model-1'].parts['col'].seedEdgeBySize(constraint=FINER, 
    deviationFactor=0.1, edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:29 #10000000 #10282a50 #1410a8 #0 #4000000 #0', 
    ' #100000 #c0000000 #300d0 ]'), ), minSizeFactor=0.1, size=200.0)
mdb.models['Model-1'].parts['col'].seedEdgeBySize(constraint=FINER, 
    deviationFactor=0.1, edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:29 #90000000 #102c2ad4 #29694a8 #0 #4c800000 #4044', 
    ' #100000 #c4000000 #2306d0 ]'), ), minSizeFactor=0.1, size=200.0)
mdb.models['Model-1'].parts['col'].seedEdgeBySize(constraint=FINER, 
    deviationFactor=0.1, edges=
    mdb.models['Model-1'].parts['col'].edges.getSequenceFromMask((
    '[#0:31 #88000000 #840d48a0 #804c5324 #53a00 #80000 #28000000', 
    ' #cf92f ]'), ), minSizeFactor=0.1, size=200.0)
# Save by 14analysis on 2018_10_09-17.47.01; build 6.12-1 2012_03_13-20.23.18 119612
mdb.models['Model-1'].parts['col'].generateMesh()
# Save by 14analysis on 2018_10_09-17.50.49; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['col'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=EXPLICIT), ElemType(elemCode=C3D6, 
    elemLibrary=EXPLICIT), ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)), 
    regions=(mdb.models['Model-1'].parts['col'].cells.getSequenceFromMask((
    '[#ffffffff:5 #fffffff ]', ), ), ))
# Save by 14analysis on 2018_10_09-18.03.13; build 6.12-1 2012_03_13-20.23.18 119612

#-------------------------------------------------------------------------------------------
#サーフェス

mdb.models['Model-1'].parts['col'].Surface(name='c00', side1Faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:21 #c30000 ]', ), ))
mdb.models['Model-1'].parts['col'].Surface(name='c01', side1Faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:21 #3c0000 ]', ), ))
mdb.models['Model-1'].parts['col'].Surface(name='c02', side1Faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:21 #300c000 ]', ), ))
mdb.models['Model-1'].parts['col'].Surface(name='c03', side1Faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:21 #c003000 ]', ), ))
# Save by 14analysis on 2018_10_09-18.06.35; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['col'].Set(faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:18 #10 #0:5 #40000000 #30249112 #200006 ]', ), ), name='e0')
mdb.models['Model-1'].parts['col'].Set(faces=
    mdb.models['Model-1'].parts['col'].faces.getSequenceFromMask((
    '[#0:19 #20000 #0:4 #10000000 #61248a1 #100018 ]', ), ), name='e1')
# Save by 14analysis on 2018_10_30-13.59.23; build 6.12-1 2012_03_13-20.23.18 119612



