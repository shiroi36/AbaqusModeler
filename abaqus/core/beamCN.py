
#-------------------------------------------------------------------------------------------
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
g[12], g[13], g[14], g[15], g[16], g[17]))
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
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=6469.78, gridSpacing=161.74, transform=t)
g, v, d1, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['beam']
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b02'])
session.viewports['Viewport: 1'].view.fitView()
s1.move(vector=(0.0, lb/2), objectList=(g[18], g[19], g[20], g[21], g[22], 
g[23], g[24], g[25], g[26], g[27], g[28], g[29], g[30], g[31], g[32], 
g[33], g[34], g[35], g[36], g[37], g[38], g[39], g[40], g[41], g[42], 
g[43], g[44]))
p = mdb.models['Model-1'].parts['beam']
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#ff17fff8 #fff ]', ), )
f1, e1, d2 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d2[2], sketchUpEdge=e1[65], 
faces=pickedFaces, sketchPlaneSide=SIDE1, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']

#フランジパーテション

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[95], normal=e[124], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[98], normal=e1[283], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[94], normal=e[141], 
cells=pickedCells)


p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#f ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[79], normal=e1[118], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#71 ]', ), )
e, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[85], normal=e[116], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#708 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[24], normal=e1[29], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4086 ]', ), )
e, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[46], normal=e[37], 
cells=pickedCells)


p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#40000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[250], )
p.PartitionCellByExtrudeEdge(line=e1[14], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[255], )
p.PartitionCellByExtrudeEdge(line=e[16], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#100000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[260], )
p.PartitionCellByExtrudeEdge(line=e1[18], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#200000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[265], )
p.PartitionCellByExtrudeEdge(line=e[20], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#400000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[270], )
p.PartitionCellByExtrudeEdge(line=e1[22], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#800000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[275], )
p.PartitionCellByExtrudeEdge(line=e[24], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1000000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[263], )
p.PartitionCellByExtrudeEdge(line=e1[26], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[266], )
p.PartitionCellByExtrudeEdge(line=e[28], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[269], )
p.PartitionCellByExtrudeEdge(line=e1[30], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[272], )
p.PartitionCellByExtrudeEdge(line=e[32], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[275], )
p.PartitionCellByExtrudeEdge(line=e1[34], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[278], )
p.PartitionCellByExtrudeEdge(line=e[36], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)


p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2000000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[229], )
p.PartitionCellByExtrudeEdge(line=e1[152], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4000000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[232], )
p.PartitionCellByExtrudeEdge(line=e[154], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8000000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[235], )
p.PartitionCellByExtrudeEdge(line=e1[156], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10000000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[238], )
p.PartitionCellByExtrudeEdge(line=e[158], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20000000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[241], )
p.PartitionCellByExtrudeEdge(line=e1[160], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#40000000 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[244], )
p.PartitionCellByExtrudeEdge(line=e[162], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80000000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[222], )
p.PartitionCellByExtrudeEdge(line=e1[162], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[225], )
p.PartitionCellByExtrudeEdge(line=e[166], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[228], )
p.PartitionCellByExtrudeEdge(line=e1[166], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[231], )
p.PartitionCellByExtrudeEdge(line=e[168], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[234], )
p.PartitionCellByExtrudeEdge(line=e1[170], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d = p.edges, p.datums
pickedEdges =(e[237], )
p.PartitionCellByExtrudeEdge(line=e[172], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)


p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #2aa800 ]', ), )
p.Surface(side1Faces=side1Faces, name='b2')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #aa800000 #2 ]', ), )
p.Surface(side1Faces=side1Faces, name='b1')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #55000000 #5 ]', ), )
p.Surface(side1Faces=side1Faces, name='b3')
p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:5 #555000 ]', ), )
p.Surface(side1Faces=side1Faces, name='b4')

p = mdb.models['Model-1'].parts['beam']
f = p.faces
faces = f.getSequenceFromMask(mask=('[#0 #804021 #0:4 #1000000 ]', ), )
p.Set(faces=faces, name='end')




p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#29ffffff #463 ]', ), )
p.deleteMesh(regions=pickedRegions)
session.viewports['Viewport: 1'].view.setValues(nearPlane=3209.58, 
farPlane=5332.76, width=165.084, height=110.67, viewOffsetX=-156.4, 
viewOffsetY=20.2614)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2002 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[55], normal=e1[61], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v[75], normal=e[89], cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#fc2fc20 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[50], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[88], rule=MIDDLE))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#be081020 #3e0 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[133], cells=pickedCells, 
point=p.InterestingPoint(edge=e[288], rule=MIDDLE))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#a0000000 #3d03d #420 ]', ), )
e1, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[320], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[326], rule=MIDDLE))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1fea #a0000800 #1000000 ]', ), )
e, v, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[222], cells=pickedCells, 
point=p.InterestingPoint(edge=e[208], rule=MIDDLE))


p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0:2 #f0d00000 #1 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:2 #f0d00000 #1 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #5000000 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #5000000 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0:2 #88200000 #81 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:2 #88200000 #81 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0:2 #44400000 #18 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0:2 #44400000 #18 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#ffffdfbf #daffffff #ffdff #42 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffdfbf #daffffff #ffdff #42 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#fc001fbf #a8103ff ]', ), )
p.setMeshControls(regions=pickedRegions, allowMapped=False)


meshsize=30.0


p = mdb.models['Model-1'].parts['beam']
p.seedPart(size=meshsize, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:20 #3ffe0780 #e003fc0 #fe0003f8 #4e00781f #3464270 #2084 ]', ), )
p.seedEdgeBySize(edges=pickedEdges, size=200.0, deviationFactor=0.1, 
minSizeFactor=0.1, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges2 = e.getSequenceFromMask(mask=(
'[#0:20 #c0000060 #60000000 #600000 #0 #18180802 #38000008', ' #9 ]', 
), )
p.seedEdgeByBias(biasMethod=SINGLE, end2Edges=pickedEdges2, minSize=meshsize, 
maxSize=200.0, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:7 #79970000 #62779de7 #3cb9e #0:14 #e0000000 #1003 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:7 #86680000 #81886218 #100401 #0:7 #4310cd00 #8030310c', 
' #80 #0:5 #bcf70 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0 #6db68000 #11554554 #4b556db4 #2aa5 #0:15 #2 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:11 #aa2db6d0 #b6822aa2 #54a96aad #5 #0:4 #1 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:17 #bcef3286 #73cc4ef3 #11e #0:5 #1f40000 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)
session.viewports['Viewport: 1'].view.setValues(session.views['Right'])

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=(
'[#f81f407f #601fc07 #20fff00 #40 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#112495 #12492000 #40008000 #0 #81000000 #a802a00a #2802a00', 
' #10a8 #8000000 #0 #92495000 #0 #49200000 #400012', 
' #28480480 #12804a01 #804a04a0 #10 #0 #80020200 #800 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#7e0bf80 #f9fe03f8 #ff #6 ]', ), 
)
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#4a800000 #492 #0 #9249 #14240002 #1500540 #50150054', 
' #8402 #0 #92a00000 #224 #124924 #80000 #0', 
' #202a000 #40150054 #15005005 #20 #10000 #2800800 #0:3', ' #200 ]', ), 
)
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)


elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:3 #ff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff:3 #ff ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts['beam']
p.SectionAssignment(region=region, sectionName=mat, offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)




p = mdb.models['Model-1'].parts['beam']
p.generateMesh()


