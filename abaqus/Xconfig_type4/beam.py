
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


#フランジパーテション
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

#ウェブパーテション

mdb.models['Model-1'].ConstrainedSketch(gridSpacing=107.17, name='__profile__', 
    sheetSize=4286.83, transform=
    mdb.models['Model-1'].parts['beam'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[586], 
    sketchOrientation=RIGHT, origin=(99.5, 0.0, 750.0)))
mdb.models['Model-1'].parts['beam'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
del mdb.models['Model-1'].sketches['__profile__']
mdb.models['Model-1'].ConstrainedSketch(gridSpacing=107.17, name='__profile__', 
    sheetSize=4286.83, transform=
    mdb.models['Model-1'].parts['beam'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[621], 
    sketchOrientation=RIGHT, origin=(99.5, 0.0, 750.0)))
mdb.models['Model-1'].parts['beam'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
del mdb.models['Model-1'].sketches['__profile__']
mdb.models['Model-1'].ConstrainedSketch(gridSpacing=107.17, name='__profile__', 
    sheetSize=4286.83, transform=
    mdb.models['Model-1'].parts['beam'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[645], 
    sketchOrientation=RIGHT, origin=(99.5, 0.0, 750.0)))
mdb.models['Model-1'].parts['beam'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
del mdb.models['Model-1'].sketches['__profile__']
mdb.models['Model-1'].ConstrainedSketch(gridSpacing=107.17, name='__profile__', 
    sheetSize=4286.83, transform=
    mdb.models['Model-1'].parts['beam'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[498], 
    sketchOrientation=RIGHT, origin=(99.5, 0.0, 750.0)))
mdb.models['Model-1'].parts['beam'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
mdb.models['Model-1'].sketches['__profile__'].sketchOptions.setValues(
    gridOrigin=(-750.0, 0.0))
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['b03'])
mdb.models['Model-1'].sketches['__profile__'].move(objectList=(
    mdb.models['Model-1'].sketches['__profile__'].geometry[34], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[35], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[36], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[37], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[38]), vector=(
    -40+2*lb-40, 0.0))
mdb.models['Model-1'].parts['beam'].CutExtrude(flipExtrudeDirection=ON, sketch=
    mdb.models['Model-1'].sketches['__profile__'], sketchOrientation=RIGHT, 
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], sketchPlaneSide=
    SIDE1, sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[498])
del mdb.models['Model-1'].sketches['__profile__']


mdb.models['Model-1'].ConstrainedSketch(gridSpacing=107.17, name='__profile__', 
    sheetSize=4286.83, transform=
    mdb.models['Model-1'].parts['beam'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['beam'].datums[3], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['beam'].edges[498], 
    sketchOrientation=RIGHT, origin=(99.5, 0.0, 750.0)))
mdb.models['Model-1'].parts['beam'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
mdb.models['Model-1'].sketches['__profile__'].sketchOptions.setValues(
    gridOrigin=(-750.0, 0.0))
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['b04'])
mdb.models['Model-1'].sketches['__profile__'].move(objectList=(
    mdb.models['Model-1'].sketches['__profile__'].geometry[31], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[32], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[33], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[34], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[35], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[36], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[37], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[38], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[39], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[40], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[41], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[42], 
    mdb.models['Model-1'].sketches['__profile__'].geometry[43]), vector=(
    -40+2*lb-40, 0.0))
    
    
    
mdb.models['Model-1'].parts['beam'].PartitionFaceBySketch(faces=
    mdb.models['Model-1'].parts['beam'].faces.getSequenceFromMask(('[#40 ]', ), 
    ), sketch=mdb.models['Model-1'].sketches['__profile__'], sketchUpEdge=
    mdb.models['Model-1'].parts['beam'].edges[14])
del mdb.models['Model-1'].sketches['__profile__']
mdb.models['Model-1'].parts['beam'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#0:2 #1000 ]', ), ), normal=mdb.models['Model-1'].parts['beam'].edges[17]
    , point=mdb.models['Model-1'].parts['beam'].vertices[17])
mdb.models['Model-1'].parts['beam'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#0:2 #2000 ]', ), ), normal=mdb.models['Model-1'].parts['beam'].edges[0], 
    point=mdb.models['Model-1'].parts['beam'].vertices[1])
mdb.models['Model-1'].parts['beam'].PartitionCellByPlanePointNormal(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#0:2 #4000 ]', ), ), normal=mdb.models['Model-1'].parts['beam'].edges[17]
    , point=mdb.models['Model-1'].parts['beam'].vertices[14])
mdb.models['Model-1'].parts['beam'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask(('[#1 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['beam'].edges[39], ), line=
    mdb.models['Model-1'].parts['beam'].edges[345], sense=REVERSE)
mdb.models['Model-1'].parts['beam'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask(('[#2 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['beam'].edges[43], ), line=
    mdb.models['Model-1'].parts['beam'].edges[515], sense=REVERSE)
mdb.models['Model-1'].parts['beam'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask(('[#4 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['beam'].edges[47], ), line=
    mdb.models['Model-1'].parts['beam'].edges[516], sense=REVERSE)
mdb.models['Model-1'].parts['beam'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask(('[#8 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['beam'].edges[51], ), line=
    mdb.models['Model-1'].parts['beam'].edges[517], sense=REVERSE)
mdb.models['Model-1'].parts['beam'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask(('[#10 ]', ), 
    ), edges=(mdb.models['Model-1'].parts['beam'].edges[55], ), line=
    mdb.models['Model-1'].parts['beam'].edges[518], sense=REVERSE)
# Save by 14analysis on 2018_10_09-19.00.09; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['beam'].Surface(name='b5', side1Faces=
    mdb.models['Model-1'].parts['beam'].faces.getSequenceFromMask((
    '[#c0000000 #7 ]', ), ))
# Save by 14analysis on 2018_10_12-12.58.45; build 6.12-1 2012_03_13-20.23.18 119612


#メッシュ分割

mdb.models['Model-1'].parts['beam'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#ffffffff:2 #ffffff ]', ), ), technique=FREE)
mdb.models['Model-1'].parts['beam'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#ffffffff:2 #ffffff ]', ), ), ))
mdb.models['Model-1'].parts['beam'].setMeshControls(elemShape=HEX, regions=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#0:2 #5800 ]', ), ), technique=STRUCTURED)
mdb.models['Model-1'].parts['beam'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=STANDARD), ElemType(elemCode=C3D6, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D4, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#0:2 #5800 ]', ), ), ))
mdb.models['Model-1'].parts['beam'].setMeshControls(elemShape=HEX, regions=
    mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#1080 #20008008 ]', ), ), technique=STRUCTURED)
mdb.models['Model-1'].parts['beam'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=STANDARD), ElemType(elemCode=C3D6, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D4, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#1080 #20008008 ]', ), ), ))
mdb.models['Model-1'].parts['beam'].seedPart(deviationFactor=0.1, 
    minSizeFactor=0.1, size=30.0)
mdb.models['Model-1'].parts['beam'].seedEdgeBySize(constraint=FINER, 
    deviationFactor=0.1, edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:17 #71fffffe #19861986 #e67 #6c800000 #49c83db3 ]', ), ), 
    minSizeFactor=0.1, size=200.0)
mdb.models['Model-1'].parts['beam'].seedEdgeByBias(biasMethod=SINGLE, 
    constraint=FINER, end1Edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:17 #8a000000 #a2492248 #10800088 #8220 ]', ), ), maxSize=200.0, 
    minSize=30.0)
mdb.models['Model-1'].parts['beam'].seedEdgeByBias(biasMethod=SINGLE, 
    constraint=FINER, end2Edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:17 #8a000000 #a2492248 #10800088 #8220 ]', ), ), maxSize=200.0, 
    minSize=30.0)
mdb.models['Model-1'].parts['beam'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#103ff0f #1ff8000 ]', ), ), number=8)
mdb.models['Model-1'].parts['beam'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:2 #80000000 #455151b6 #54546da0 #11 #955b6800 #a2da02a4', 
    ' #54b2 #0 #9ffe0000 #e3fc7fff #17 #0 #1fffffe0', 
    ' #3c6ff90f #e7ce0000 #1 #0:2 #400000 #1007c200 ]'), ), number=4)
mdb.models['Model-1'].parts['beam'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:2 #80000000 #455151b6 #54546da0 #11 #955b6800 #a2da02a4', 
    ' #54b2 #0 #14f00000 #e18c4446 #1 #0:2 #3c010000', ' #e0000 ]'), ), number=
    2)
mdb.models['Model-1'].parts['beam'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['beam'].edges.getSequenceFromMask((
    '[#0:10 #4000000 #c4440 #0:4 #c0000 ]', ), ), number=4)
    
    
# Save by 14analysis on 2018_10_12-13.04.27; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['beam'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=EXPLICIT), ElemType(elemCode=C3D6, 
    elemLibrary=EXPLICIT), ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)), 
    regions=(mdb.models['Model-1'].parts['beam'].cells.getSequenceFromMask((
    '[#ffffffff:2 #ffffff ]', ), ), ))
mdb.models['Model-1'].parts['beam'].generateMesh()
# Save by 14analysis on 2018_10_12-13.05.52; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['beam'].Set(faces=
    mdb.models['Model-1'].parts['beam'].faces.getSequenceFromMask((
    '[#0:12 #29100000 #0 #8 ]', ), ), name='e')
# Save by 14analysis on 2018_10_30-14.01.19; build 6.12-1 2012_03_13-20.23.18 119612
