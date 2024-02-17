
#パートモジュール
#スケッチの単なる押し出し。ここではtest00スケッチ3000mm押し出している。
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b00'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name='beam', dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts['beam']
p.BaseSolidExtrude(sketch=s, depth=lb)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts['beam']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']

#データム平面の使用
p = mdb.models['Model-1'].parts['beam']
e = p.edges
p.DatumPlaneByThreePoints(point1=p.InterestingPoint(edge=e[42], rule=MIDDLE), 
point2=p.InterestingPoint(edge=e[38], rule=MIDDLE), 
point3=p.InterestingPoint(edge=e[41], rule=MIDDLE))
p = mdb.models['Model-1'].parts['beam']
v1, e1 = p.vertices, p.edges
p.DatumPlaneByThreePoints(point1=v1[7], point2=p.InterestingPoint(edge=e1[5], 
rule=MIDDLE), point3=p.InterestingPoint(edge=e1[41], rule=MIDDLE))

#データムポイント
p = mdb.models['Model-1'].parts[cpartname]
p.DatumPointByCoordinate(coords=(0.0, 0.0, 0.0))
p = mdb.models['Model-1'].parts[cpartname]
p.DatumPointByCoordinate(coords=(0.0, 0.0, lb2))

#フランジのあなあけ
p = mdb.models['Model-1'].parts['beam']
e, d1 = p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[2], sketchUpEdge=e[41], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, 300.0, 
lb/2))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=6469.78, gridSpacing=161.74, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['beam']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b01'])
session.viewports['Viewport: 1'].view.fitView()
s.move(vector=(0.0, lb/2), objectList=(g[6], g[7], g[8], g[9], g[10], g[11], 
g[12], g[13]))
p = mdb.models['Model-1'].parts['beam']
e1, d2 = p.edges, p.datums
p.CutExtrude(sketchPlane=d2[2], sketchUpEdge=e1[41], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, flipExtrudeDirection=OFF)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


#フランジスケッチ
p = mdb.models['Model-1'].parts['beam']
f, e, d = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d[2], sketchUpEdge=e[65], 
sketchPlaneSide=SIDE1, origin=(0.0, 300.0, lb/2))


#フランジパーテション
#R部のパーテション
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v[40], normal=e[87], cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[71], normal=e1[91], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v[71], normal=e[95], cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[69], normal=e1[6], 
cells=pickedCells)

#スケッチと大まかなパーテション

p = mdb.models['Model-1'].parts['beam']
f, e, d = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=f[32], sketchUpEdge=e[87], 
sketchPlaneSide=SIDE1, origin=(0.0, 300.0, 884.814053))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=3610.2, gridSpacing=90.25, transform=t)
g, v, d1, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['beam']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b02'])
s.move(vector=(0.0, 884.814053), objectList=(g[14], g[15], g[16], g[17], g[18], 
g[19], g[20], g[21], g[22], g[23], g[24], g[25], g[26], g[27], g[28], 
g[29], g[30], g[31], g[32]))
p = mdb.models['Model-1'].parts['beam']
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#0 #1 ]', ), )
e1, d2 = p.edges, p.datums
p.PartitionFaceBySketch(sketchUpEdge=e1[87], faces=pickedFaces, sketch=s)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1f ]', ), )
e, v2, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[16], normal=e[19], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#9b ]', ), )
e1, v1, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[25], normal=e1[31], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#351 ]', ), )
e, v2, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[49], normal=e[76], 
cells=pickedCells)

#細かなパーテーション

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#c0000 ]', ), )
e1, d = p.edges, p.datums
pickedEdges =(e1[201], )
p.PartitionCellByExtrudeEdge(line=e1[104], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#100001 ]', ), )
e, d2 = p.edges, p.datums
pickedEdges =(e[212], )
p.PartitionCellByExtrudeEdge(line=e[131], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#400002 ]', ), )
e1, d = p.edges, p.datums
pickedEdges =(e1[212], )
p.PartitionCellByExtrudeEdge(line=e1[135], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1000002 ]', ), )
e, d2 = p.edges, p.datums
pickedEdges =(e[212], )
p.PartitionCellByExtrudeEdge(line=e[139], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4000002 ]', ), )
e1, d = p.edges, p.datums
pickedEdges =(e1[217], )
p.PartitionCellByExtrudeEdge(line=e1[143], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10000008 ]', ), )
e, d2 = p.edges, p.datums
pickedEdges =(e[223], )
p.PartitionCellByExtrudeEdge(line=e[161], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#21 ]', ), )
e1, d = p.edges, p.datums
pickedEdges =(e1[229], )
p.PartitionCellByExtrudeEdge(line=e1[165], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#84 ]', ), )
e, d2 = p.edges, p.datums
pickedEdges =(e[235], )
p.PartitionCellByExtrudeEdge(line=e[169], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#202 ]', ), )
e1, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[56], normal=e1[56], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#9 ]', ), )
e, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[18], normal=e[22], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#ff008 #80 ]', ), )
e1, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[35], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[254], rule=CENTER))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3fd000 #10000 ]', ), )
e, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[186], cells=pickedCells, 
point=p.InterestingPoint(edge=e[395], rule=CENTER))

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80000 #2 ]', ), )
e1, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[255], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[264], rule=MIDDLE))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#a0000 ]', ), )
e, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[214], cells=pickedCells, 
point=p.InterestingPoint(edge=e[211], rule=MIDDLE))

#材料特性
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:2 #ffff ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts['beam']
p.SectionAssignment(region=region, sectionName='steel', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


#メッシュの色分け

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0:2 #6a2 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:2 #6a2 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #f0000000 #1 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #f0000000 #1 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #10000000 #2d07 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #10000000 #2d07 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #2c20 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #2c20 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#f7ffffef #fdffc7f #c000 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#f7ffffef #fdffc7f #c000 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))

#メッシュ分割

p = mdb.models['Model-1'].parts['beam']
p.seedPart(size=15.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:15 #1fffffe0 #98619867 #e671 #80000000 #f6c30cc #24d2 ]', ), )
p.seedEdgeBySize(edges=pickedEdges, size=200.0, deviationFactor=0.1, 
minSizeFactor=0.1, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges2 = e.getSequenceFromMask(mask=(
'[#0:15 #a0000000 #24922488 #800088a #410801 ]', ), )
p.seedEdgeByBias(biasMethod=SINGLE, end2Edges=pickedEdges2, minSize=15.0, 
maxSize=200.0, constraint=FINER)

p = mdb.models['Model-1'].parts['beam']
p.generateMesh()
elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:2 #ffff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))


#パーテション
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #40400000 #84 #0:5 #550 ]', ), 
)
p.Surface(side1Faces=side1Faces, name='b1')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:3 #1008000 #21000 #0:6 #50000000 #5 ]', ), )
p.Surface(side1Faces=side1Faces, name='b2')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=(
'[#0:3 #10080000 #84 #0:6 #a8000000 #2 ]', ), )
p.Surface(side1Faces=side1Faces, name='b3')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #4040000 #88000 #0:5 #2a8 ]', 
), )
p.Surface(side1Faces=side1Faces, name='b4')




