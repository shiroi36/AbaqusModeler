

#スケッチの単なる読み込み
from dxf2abq import importdxf
importdxf(fileName='lib/'+s0+'.dxf')
importdxf(fileName='lib/'+s1+'.dxf')
#-------------------------------------------------------------------------------------------
#パートモジュール


s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['cwr'])
session.viewports['Viewport: 1'].view.fitView()
session.viewports['Viewport: 1'].view.setValues(width=446.808, height=333.801, 
cameraPosition=(5.05599, -5.30846, 847.585), cameraTarget=(5.05599, 
-5.30846, 0))
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s, depth=tcwr)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts[cpartname]
f, e, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=f[16], sketchUpEdge=e[10], 
sketchPlaneSide=SIDE1, origin=(0.0, 0.0, 12.0))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=847.92, gridSpacing=21.19, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['cwr2'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#10000 ]', ), )
e1, d2 = p.edges, p.datums
p.PartitionFaceBySketch(sketchUpEdge=e1[10], faces=pickedFaces, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[18], )
p.PartitionCellByExtrudeEdge(line=e[32], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[19], )
p.PartitionCellByExtrudeEdge(line=e1[46], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[22], )
p.PartitionCellByExtrudeEdge(line=e[47], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[44], )
p.PartitionCellByExtrudeEdge(line=e1[48], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[40], )
p.PartitionCellByExtrudeEdge(line=e[49], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[25], )
p.PartitionCellByExtrudeEdge(line=e1[50], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[38], )
p.PartitionCellByExtrudeEdge(line=e[51], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[32], )
p.PartitionCellByExtrudeEdge(line=e1[52], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[37], )
p.PartitionCellByExtrudeEdge(line=e[53], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[43], )
p.PartitionCellByExtrudeEdge(line=e1[54], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[42], )
p.PartitionCellByExtrudeEdge(line=e[55], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[41], )
p.PartitionCellByExtrudeEdge(line=e1[56], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)


p = mdb.models['Model-1'].parts['cwr']
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#1fff ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts['cwr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['cwr']
p.seedPart(size=30.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts['cwr']
p.generateMesh()
elemType1 = mesh.ElemType(elemCode=C3D20R, elemLibrary=STANDARD)
elemType2 = mesh.ElemType(elemCode=C3D15, elemLibrary=STANDARD)
elemType3 = mesh.ElemType(elemCode=C3D10, elemLibrary=STANDARD)
p = mdb.models['Model-1'].parts['cwr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
elemType1 = mesh.ElemType(elemCode=UNKNOWN_HEX, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=UNKNOWN_WEDGE, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D10M, elemLibrary=EXPLICIT, 
secondOrderAccuracy=OFF, distortionControl=DEFAULT)
p = mdb.models['Model-1'].parts['cwr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
session.viewports['Viewport: 1'].view.setValues(nearPlane=824.72, 
farPlane=1019.97, width=354.546, height=266.04, cameraPosition=(245.27, 
-79.5875, 891.785), cameraUpVector=(0.118446, 0.945808, -0.302355), 
cameraTarget=(15.2915, -10.3623, -0.794978))
session.viewports['Viewport: 1'].view.setValues(nearPlane=823.805, 
farPlane=1020.89, width=335.633, height=251.849, viewOffsetX=4.0481, 
viewOffsetY=11.0786)
session.viewports['Viewport: 1'].view.setValues(nearPlane=821.095, 
farPlane=1002.73, width=334.529, height=251.02, cameraPosition=(
-175.998, -55.7231, 899.216), cameraUpVector=(0.222433, 0.943236, 
-0.246637), cameraTarget=(16.7454, -10.6088, -3.66621), 
viewOffsetX=4.03478, viewOffsetY=11.0422)
p = mdb.models['Model-1'].parts['cwr']
p.generateMesh()
session.viewports['Viewport: 1'].view.setValues(nearPlane=816.014, 
farPlane=1007.81, width=400.271, height=300.351, viewOffsetX=9.69997, 
viewOffsetY=-1.34095)
session.viewports['Viewport: 1'].view.setValues(session.views['Front'])


elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT, 
secondOrderAccuracy=OFF, distortionControl=DEFAULT)
p = mdb.models['Model-1'].parts['cwr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['cfr']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT, 
secondOrderAccuracy=OFF, distortionControl=DEFAULT)
p = mdb.models['Model-1'].parts['cfr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts['col']
session.viewports['Viewport: 1'].setValues(displayedObject=p)


p = mdb.models['Model-1'].parts['cwr']
e = p.edges
edges = e.getSequenceFromMask(mask=('[#f00 ]', ), )
p.Set(edges=edges, name='weld')


p = mdb.models['Model-1'].parts['cwr']
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1fff ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts['cwr']
p.SectionAssignment(region=region, sectionName=mat, offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


p = mdb.models['Model-1'].parts['cwr']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0 #7e000 ]', ), )
p.Surface(side1Faces=side1Faces, name='b0')
p = mdb.models['Model-1'].parts['cwr']
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0 #81f00 ]', ), )
p.Surface(side1Faces=side1Faces, name='b1')


