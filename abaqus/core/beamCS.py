
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



#ウェブのあなあけ

p = mdb.models['Model-1'].parts['beam']
e1, d2 = p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d2[3], sketchUpEdge=e1[126], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(100.0, 0.0, 
lb/2))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=6469.78, gridSpacing=161.74, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['beam']
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b03'])
session.viewports['Viewport: 1'].view.fitView()
s.move(vector=(lb/2, 0.0), objectList=(g[30], g[31], g[32], g[33], g[34], 
g[35]))
p = mdb.models['Model-1'].parts['beam']
e, d1 = p.edges, p.datums
p.CutExtrude(sketchPlane=d1[3], sketchUpEdge=e[126], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, flipExtrudeDirection=ON)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


p = mdb.models['Model-1'].parts['beam']
f, e1, d2 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d2[3], sketchUpEdge=e1[138], 
sketchPlaneSide=SIDE2, origin=(100.0, 0.0, lb/2))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=6469.78, gridSpacing=161.74, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts['beam']
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['b04'])
session.viewports['Viewport: 1'].view.fitView()
s1.move(vector=(lb/2, 0.0), objectList=(g[30], g[31], g[32], g[33], g[34], 
g[35], g[36], g[37], g[38], g[39], g[40], g[41], g[42], g[43], g[44]))
p = mdb.models['Model-1'].parts['beam']
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=(
'[#ffffffff #7fbffeff #ffffffff:4 #7fffffff ]', ), )
f1, e, d1 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d1[3], sketchUpEdge=e[138], 
faces=pickedFaces, sketchPlaneSide=SIDE2, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']



#パーテション
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10000000 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[47], normal=e1[49], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20000000 ]', ), )
e, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[12], normal=e[15], 
cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#40000000 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[0], normal=e1[5], cells=pickedCells)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80000000 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[66], )
p.PartitionCellByExtrudeEdge(line=e[140], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[64], )
p.PartitionCellByExtrudeEdge(line=e1[140], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[62], )
p.PartitionCellByExtrudeEdge(line=e[140], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[60], )
p.PartitionCellByExtrudeEdge(line=e1[140], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[58], )
p.PartitionCellByExtrudeEdge(line=e[140], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[56], )
p.PartitionCellByExtrudeEdge(line=e1[140], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)

p = mdb.models['Model-1'].parts['beam']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0 #7e ]', ), )
p.Surface(side1Faces=side1Faces, name='b5')




#材料特性
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff #1fffff ]', ), )
region = p.Set(cells=cells, name='Set-1')
p = mdb.models['Model-1'].parts['beam']
p.SectionAssignment(region=region, sectionName=mat, offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)




#メッシュ
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #103104 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #103104 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #103104 ]', ), )
p.setMeshControls(regions=pickedRegions, allowMapped=False)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #103104 ]', ), )
p.setMeshControls(regions=pickedRegions, allowMapped=True)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #30888 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #30888 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#ffffffff #84223 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff #84223 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#1c0 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=HEX, technique=STRUCTURED)
elemType1 = mesh.ElemType(elemCode=C3D8R)
elemType2 = mesh.ElemType(elemCode=C3D6)
elemType3 = mesh.ElemType(elemCode=C3D4)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1c0 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
session.viewports['Viewport: 1'].view.setValues(session.views['Front'])
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#0 #3e1a ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#0 #3e1a ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))


p = mdb.models['Model-1'].parts['beam']
p.seedPart(size=15.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#0:4 #fe000388 #703fc3f #e380007f #f0003f #32138138 #80020068', 
' #1000 ]', ), )
p.seedEdgeBySize(edges=pickedEdges, size=200.0, deviationFactor=0.1, 
minSizeFactor=0.1, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges2 = e.getSequenceFromMask(mask=(
'[#0:4 #6 #300000c0 #180000 #0 #400800 #40603', ' #0 #49c0000 ]', ), )
p.seedEdgeByBias(biasMethod=SINGLE, end2Edges=pickedEdges2, minSize=15.0, 
maxSize=200.0, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#0:4 #52800 #280000 #0 #a052800 ]', 
), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#fff #ffbffc00 #ffffe7e7 #1fffffff #0:5 #7ff9f800 #7fffefff ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)


p = mdb.models['Model-1'].parts['beam']
p.generateMesh()



p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#fffffe00 #84001 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#fffffe00 #84001 ]', ), )
p.setMeshControls(regions=pickedRegions, allowMapped=True)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#3d #20 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#0 #ffbffc00 #7 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=4, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
p.generateMesh()
elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff #1fffff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))

p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#fde00000 #40 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#0:4 #52800 #0:6 #1aaaa ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#3fe00 #8000 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#0:7 #52800 #0:3 #5555 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)


p = mdb.models['Model-1'].parts['beam']
p.generateMesh()



p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#3d #20 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=('[#0:2 #7e0 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=2, constraint=FINER)
p = mdb.models['Model-1'].parts['beam']
p.generateMesh()
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#3d #20 ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts['beam']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#3d #20 ]', ), )
p.setMeshControls(regions=pickedRegions, allowMapped=True)
p = mdb.models['Model-1'].parts['beam']
p.generateMesh()






