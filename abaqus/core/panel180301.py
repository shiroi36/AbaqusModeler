

#-------------------------------------------------------------------------------------------

#スケッチの単なる読み込み
from dxf2abq import importdxf
importdxf(fileName='lib/'+s0+'.dxf')
importdxf(fileName='lib/'+s0+'0.dxf')
importdxf(fileName='lib/'+s0+'1.dxf')
importdxf(fileName='lib/'+s0+'2.dxf')
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=STANDALONE)
s1.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches[cpartname])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s1, depth=tp)
s1.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[0], cells=pickedCells, 
point=p.InterestingPoint(edge=e[0], rule=MIDDLE))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[11], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[11], rule=MIDDLE))
session.viewports['Viewport: 1'].partDisplay.setValues(mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=ON)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=OFF)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=25, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()
session.viewports['Viewport: 1'].partDisplay.setValues(mesh=OFF)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=OFF)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=ON)



#-------------------------------------------------------------------------------------------


p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#f ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName=mat, offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getSequenceFromMask(mask=('[#80460 ]', ), )
p.Set(faces=faces, name='Set-1')




#-------------------------------------------------------------------------------------------

session.viewports['Viewport: 1'].view.setValues(nearPlane=1889.11, 
farPlane=2484.2, width=1234.98, height=864.272, cameraPosition=(
1130.48, 278.849, 1857.09), cameraUpVector=(-0.173718, 0.896056, 
-0.408541), cameraTarget=(-6.65991, 24.4627, -19.8922))
session.viewports['Viewport: 1'].view.setValues(nearPlane=1918.02, 
farPlane=2452.82, width=1253.88, height=877.5, cameraPosition=(666.967, 
315.317, 2063.33), cameraUpVector=(-0.0622479, 0.887243, -0.457083), 
cameraTarget=(-1.86741, 24.0856, -22.0246))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#f ]', ), )
p.deleteMesh(regions=pickedRegions)
p = mdb.models['Model-1'].parts[cpartname]
f, e, d = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=f[3], sketchUpEdge=e[12], 
sketchPlaneSide=SIDE1, origin=(98.0, -244.0, 12.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=1052.05, gridSpacing=26.3, transform=t)
g, v, d1, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['panel0'])
session.viewports['Viewport: 1'].view.fitView()
s.move(vector=(-98.0, 244.0), objectList=(g[18], g[19]))
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#41088 ]', ), )
e1, d2 = p.edges, p.datums
p.PartitionFaceBySketch(sketchUpEdge=e1[12], faces=pickedFaces, sketch=s)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']
session.viewports['Viewport: 1'].view.setValues(nearPlane=2009.13, 
farPlane=2361.71, width=357.902, height=250.469, viewOffsetX=120.317, 
viewOffsetY=-280.854)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#c ]', ), )
e, v1, d = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v1[4], normal=e[7], cells=pickedCells)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2009.13, 
farPlane=2361.71, width=357.902, height=250.469, viewOffsetX=-108.445, 
viewOffsetY=273.757)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#c ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(point=v2[18], normal=e1[35], 
cells=pickedCells)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1980.01, 
farPlane=2390.84, width=794.928, height=556.312, viewOffsetX=-15.9099, 
viewOffsetY=239.3)
session.viewports['Viewport: 1'].view.setValues(session.views['Front'])

session.viewports['Viewport: 1'].view.setValues(nearPlane=1874.37, 
farPlane=2333.02, width=1308.46, height=915.697, cameraPosition=(
635.959, -9.45345, 2011.24), cameraUpVector=(-0.0820365, 0.996156, 
0.0307139))
session.viewports['Viewport: 1'].view.setValues(nearPlane=1887.35, 
farPlane=2320.04, width=1550.03, height=1084.75, viewOffsetX=-28.3802, 
viewOffsetY=12.625)
p = mdb.models['Model-1'].parts[cpartname]
f1, e = p.faces, p.edges
t = p.MakeSketchTransform(sketchPlane=f1[25], sketchUpEdge=e[16], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(-98.0, 418.0, 
12.0))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=2103.55, gridSpacing=52.58, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
session.viewports['Viewport: 1'].view.setValues(nearPlane=221.995, 
farPlane=753.466, width=2728.99, height=1909.82, cameraPosition=(
-428.203, 343.57, 493.73), cameraTarget=(-428.203, 343.57, 12))
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(-88.8465, 
94.5859, 493.73), cameraTarget=(-88.8465, 94.5859, 12))
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['panel1'])
session.viewports['Viewport: 1'].view.fitView()
session.viewports['Viewport: 1'].view.setValues(nearPlane=2795.33, 
farPlane=2977.99, width=716.376, height=501.339, cameraPosition=(
4.0197, 80.465, 2892.66), cameraTarget=(4.0197, 80.465, 6))
s1.move(vector=(98.0, -418.0), objectList=(g[34], g[35], g[36], g[37], g[38], 
g[39], g[40], g[41], g[42], g[43], g[44], g[45], g[46], g[47], g[48], 
g[49], g[50], g[51], g[52], g[53], g[54], g[55], g[56], g[57]))
session.viewports['Viewport: 1'].view.setValues(nearPlane=2676.91, 
farPlane=3096.41, width=1935.84, height=1354.75, cameraPosition=(
8.44261, 212.585, 2892.66), cameraTarget=(8.44261, 212.585, 6))
session.viewports['Viewport: 1'].view.setValues(nearPlane=1688.35, 
farPlane=2424.77, width=1386.6, height=970.379, cameraPosition=(
1675.49, 26.5453, 1242.49), cameraUpVector=(-0.037534, 0.998891, 
0.0284108), cameraTarget=(-17.7013, -1.57783, -5.63525), 
viewOffsetX=-25.388, viewOffsetY=11.2939)
p = mdb.models['Model-1'].parts[cpartname]
f, e1 = p.faces, p.edges
p.CutExtrude(sketchPlane=f[25], sketchUpEdge=e1[16], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s1, flipExtrudeDirection=OFF)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


p = mdb.models['Model-1'].parts[cpartname]
f1, e, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=f1[25], sketchUpEdge=e[83], 
sketchPlaneSide=SIDE1, origin=(-98.26268, -418.0, 12.0))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=1990.97, gridSpacing=49.77, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
session.viewports['Viewport: 1'].view.setValues(nearPlane=252.869, 
farPlane=722.591, width=1737.32, height=1215.82, cameraPosition=(
-90.5144, -88.2548, 493.73), cameraTarget=(-90.5144, -88.2548, 12))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches['panel2'])
session.viewports['Viewport: 1'].view.fitView()
session.viewports['Viewport: 1'].view.setValues(nearPlane=2873.67, 
farPlane=2945.68, width=251.863, height=176.26, cameraPosition=(
-2.5897, -2.60302, 2915.67), cameraTarget=(-2.5897, -2.60302, 6))
s.move(vector=(98.26268, 418.0), objectList=(g[58], g[59], g[60], g[61], g[62], 
g[63], g[64], g[65], g[66], g[67], g[68], g[69], g[70], g[71], g[72], 
g[73], g[74], g[75], g[76], g[77], g[78], g[79], g[80], g[81]))
session.viewports['Viewport: 1'].view.setValues(nearPlane=2760.44, 
farPlane=3058.91, width=1360.83, height=952.346, cameraPosition=(
-31.7234, 51.3274, 2915.67), cameraTarget=(-31.7234, 51.3274, 6))
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#96000000 ]', ), )
e1, d2 = p.edges, p.datums
p.PartitionFaceBySketch(sketchUpEdge=e1[83], faces=pickedFaces, sketch=s)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']
session.viewports['Viewport: 1'].view.setValues(nearPlane=1986.95, 
farPlane=2220.43, width=377.959, height=264.506, viewOffsetX=-64.9588, 
viewOffsetY=347.276)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[36], )
p.PartitionCellByExtrudeEdge(line=e[106], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#40 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[45], )
p.PartitionCellByExtrudeEdge(line=e1[107], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[49], )
p.PartitionCellByExtrudeEdge(line=e[108], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[47], )
p.PartitionCellByExtrudeEdge(line=e1[109], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[56], )
p.PartitionCellByExtrudeEdge(line=e[110], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[49], )
p.PartitionCellByExtrudeEdge(line=e1[111], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1994.05, 
farPlane=2213.33, width=379.31, height=265.451, viewOffsetX=116.013, 
viewOffsetY=341.832)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[29], )
p.PartitionCellByExtrudeEdge(line=e[135], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[35], )
p.PartitionCellByExtrudeEdge(line=e1[136], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[38], )
p.PartitionCellByExtrudeEdge(line=e[137], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2005.74, 
farPlane=2201.64, width=234.31, height=163.976, viewOffsetX=106.243, 
viewOffsetY=383.446)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[35], )
p.PartitionCellByExtrudeEdge(line=e1[138], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[47], )
p.PartitionCellByExtrudeEdge(line=e[139], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[40], )
p.PartitionCellByExtrudeEdge(line=e1[140], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1926.82, 
farPlane=2280.57, width=1143.32, height=800.123, viewOffsetX=-90.6227, 
viewOffsetY=252.69)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1909.72, 
farPlane=2297.66, width=1133.17, height=793.024, viewOffsetX=38.177, 
viewOffsetY=-158.4)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2005.74, 
farPlane=2201.64, width=234.31, height=163.976, viewOffsetX=-55.8236, 
viewOffsetY=-371.957)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2010.23, 
farPlane=2197.15, width=234.835, height=164.343, viewOffsetX=-115.57, 
viewOffsetY=-382.285)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2005.59, 
farPlane=2201.79, width=293.233, height=205.212, viewOffsetX=-119.917, 
viewOffsetY=-375.191)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8000 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[75], )
p.PartitionCellByExtrudeEdge(line=e[131], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#10000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[89], )
p.PartitionCellByExtrudeEdge(line=e1[132], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#20000 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[97], )
p.PartitionCellByExtrudeEdge(line=e[133], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#40000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[91], )
p.PartitionCellByExtrudeEdge(line=e1[134], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#80000 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[90], )
p.PartitionCellByExtrudeEdge(line=e[135], cells=pickedCells, edges=pickedEdges, 
sense=REVERSE)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#100000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[96], )
p.PartitionCellByExtrudeEdge(line=e1[136], cells=pickedCells, 
edges=pickedEdges, sense=REVERSE)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1983.45, 
farPlane=2223.94, width=555.539, height=388.781, viewOffsetX=-147.101, 
viewOffsetY=-355.208)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1974.61, 
farPlane=2232.78, width=553.064, height=387.049, viewOffsetX=99.424, 
viewOffsetY=-382.296)
session.viewports['Viewport: 1'].view.setValues(nearPlane=1983.89, 
farPlane=2223.49, width=401.467, height=280.957, viewOffsetX=76.8017, 
viewOffsetY=-379.217)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#100000 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[71], )
p.PartitionCellByExtrudeEdge(line=e[146], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
session.viewports['Viewport: 1'].view.setValues(nearPlane=2012.77, 
farPlane=2194.61, width=180.726, height=126.477, viewOffsetX=12.2923, 
viewOffsetY=-429.724)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#200000 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[88], )
p.PartitionCellByExtrudeEdge(line=e1[147], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[95], )
p.PartitionCellByExtrudeEdge(line=e[148], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#2 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[88], )
p.PartitionCellByExtrudeEdge(line=e1[149], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#4 ]', ), )
e, d1 = p.edges, p.datums
pickedEdges =(e[88], )
p.PartitionCellByExtrudeEdge(line=e[150], cells=pickedCells, edges=pickedEdges, 
sense=FORWARD)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#8 ]', ), )
e1, d2 = p.edges, p.datums
pickedEdges =(e1[92], )
p.PartitionCellByExtrudeEdge(line=e1[151], cells=pickedCells, 
edges=pickedEdges, sense=FORWARD)


session.viewports['Viewport: 1'].partDisplay.setValues(mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=ON)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=OFF)
session.viewports['Viewport: 1'].view.setValues(session.views['Front'])
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#2effffff ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#2effffff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getSequenceFromMask(mask=(
'[#e3fffe1f #fffc3fff #f87fffc7 #ff1f1f0 ]', ), )
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()
elemType1 = mesh.ElemType(elemCode=C3D8R, elemLibrary=EXPLICIT)
elemType2 = mesh.ElemType(elemCode=C3D6, elemLibrary=EXPLICIT)
elemType3 = mesh.ElemType(elemCode=C3D4, elemLibrary=EXPLICIT)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#ffffffff ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
session.viewports['Viewport: 1'].view.setValues(nearPlane=2060.51, 
farPlane=2146.88, width=282.011, height=198.18, viewOffsetX=36.88, 
viewOffsetY=333.058)
session.viewports['Viewport: 1'].view.setProjection(projection=PARALLEL)


p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:2 #7e0000 ]', ), )
p.Surface(side1Faces=side1Faces, name='b0')
session.viewports['Viewport: 1'].view.setValues(nearPlane=1802.21, 
cameraPosition=(782.391, 55.4878, 2050.01), cameraTarget=(124.556, 
-645.118, 178.654))
p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:2 #1c003800 ]', ), )
p.Surface(side1Faces=side1Faces, name='b1')
session.viewports['Viewport: 1'].view.setValues(nearPlane=1739.49, 
farPlane=2423.29, width=1184.7, height=829.086, cameraPosition=(
908.888, 205.245, 1949.48), cameraTarget=(251.053, -495.361, 78.12))
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(869.13, 
483.017, 1859.46), cameraTarget=(211.295, -217.589, -11.8973))
session.viewports['Viewport: 1'].view.setValues(nearPlane=1802.21, 
farPlane=2360.57, width=379.789, height=265.786, cameraPosition=(
570.644, 737.873, 1868.97), cameraTarget=(-87.1912, 37.2669, -2.38491))
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(708.583, 
725.735, 1825.03), cameraTarget=(50.7481, 25.1287, -46.3302))
session.viewports['Viewport: 1'].view.setValues(cameraPosition=(562.48, 750.93, 
1866.96), cameraTarget=(-95.3546, 50.3234, -4.40355))
p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:2 #381c000 ]', ), )
p.Surface(side1Faces=side1Faces, name='b2')
session.viewports['Viewport: 1'].view.setValues(nearPlane=1809.72, 
farPlane=2353.07, cameraPosition=(729.62, 742.175, 1811.48), 
cameraTarget=(71.7852, 41.568, -59.8798))
p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side1Faces = s.getSequenceFromMask(mask=('[#0:2 #e0000700 ]', ), )
p.Surface(side1Faces=side1Faces, name='b3')



