
#-------------------------------------------------------------------------------------------
#パートモジュール
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=STANDALONE)
s1.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['cfr'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s1, depth=tcfr)
s1.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
session.viewports['Viewport: 1'].partDisplay.setValues(sectionAssignments=ON, 
engineeringFeatures=ON)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=OFF)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1 ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='sm490', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)
session.viewports['Viewport: 1'].partDisplay.setValues(sectionAssignments=OFF, 
engineeringFeatures=OFF, mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=ON)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedRegions = c.getSequenceFromMask(mask=('[#1 ]', ), )
p.setMeshControls(regions=pickedRegions, elemShape=TET, technique=FREE, 
allowMapped=False)
elemType1 = mesh.ElemType(elemCode=C3D20R)
elemType2 = mesh.ElemType(elemCode=C3D15)
elemType3 = mesh.ElemType(elemCode=C3D10)
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#1 ]', ), )
pickedRegions =(cells, )
p.setElementType(regions=pickedRegions, elemTypes=(elemType1, elemType2, 
elemType3))
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=25.0, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()


session.viewports['Viewport: 1'].view.setValues(nearPlane=540.122, 
farPlane=989.573, width=591.976, height=286.605, cameraPosition=(
-550.366, 530.787, 24.9567), cameraUpVector=(0.726737, 0.249987, 
0.639812), cameraTarget=(-4.85824, 6.75708, 4.32228), 
viewOffsetX=1.6681, viewOffsetY=0.21611)
session.viewports['Viewport: 1'].view.setValues(nearPlane=545.648, 
farPlane=984.068, width=598.033, height=289.537, cameraPosition=(
-503.989, 549.462, 176.59), cameraUpVector=(0.78421, 0.0632831, 
0.617261), cameraTarget=(-4.41029, 7.26874, 6.11913), 
viewOffsetX=1.68517, viewOffsetY=0.218321)
session.viewports['Viewport: 1'].view.setValues(nearPlane=541.69, 
farPlane=988.231, width=593.695, height=287.437, cameraPosition=(
540.57, 540.755, -17.1776), cameraUpVector=(0.363465, -0.816124, 
0.44926), cameraTarget=(6.37607, 5.20044, 3.56691), 
viewOffsetX=1.67294, viewOffsetY=0.216737)
session.viewports['Viewport: 1'].view.setValues(nearPlane=560.158, 
farPlane=969.787, width=613.936, height=297.237, cameraPosition=(
608.003, 344.517, 317.174), cameraUpVector=(-0.0651987, -0.936666, 
0.344102), cameraTarget=(7.83381, 3.21887, 7.45629), 
viewOffsetX=1.72998, viewOffsetY=0.224126)
session.viewports['Viewport: 1'].view.setValues(nearPlane=562.09, 
farPlane=967.887, width=616.053, height=298.262, cameraPosition=(
411.192, -532.382, 370.284), cameraUpVector=(-0.95888, -0.0735493, 
0.274117), cameraTarget=(4.30341, -7.16592, 8.06571), 
viewOffsetX=1.73594, viewOffsetY=0.224899)
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
edges = e.getSequenceFromMask(mask=('[#a44 ]', ), )
p.Set(edges=edges, name='Set-1')
session.viewports['Viewport: 1'].view.setValues(nearPlane=582.821, 
farPlane=947.133, width=638.774, height=309.262, cameraPosition=(
293.623, -520.298, -471.777), cameraUpVector=(-0.722808, -0.420707, 
0.548228), cameraTarget=(1.78159, -4.83118, -0.889768), 
viewOffsetX=1.79996, viewOffsetY=0.233194)
session.viewports['Viewport: 1'].view.setValues(nearPlane=587.885, 
farPlane=942.069, width=465.524, height=225.383, viewOffsetX=-0.905499, 
viewOffsetY=15.6136)
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
edges = e.getSequenceFromMask(mask=('[#49244a44 #92491292 #a4 ]', ), )
p.Set(edges=edges, name='Set-1')

p = mdb.models['Model-1'].parts['cfr']
e = p.edges
edges = e.getSequenceFromMask(mask=('[#a44 ]', ), )
p.Set(edges=edges, name='Set-1')


mdb.models['Model-1'].parts['cfr'].setElementType(elemTypes=(ElemType(
    elemCode=UNKNOWN_HEX, elemLibrary=EXPLICIT), ElemType(
    elemCode=UNKNOWN_WEDGE, elemLibrary=EXPLICIT), ElemType(elemCode=C3D10M, 
    elemLibrary=EXPLICIT, secondOrderAccuracy=OFF, distortionControl=DEFAULT)), 
    regions=(mdb.models['Model-1'].parts['cfr'].cells.getSequenceFromMask((
    '[#1 ]', ), ), ))
mdb.models['Model-1'].parts['cfr'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=EXPLICIT), ElemType(elemCode=C3D6, 
    elemLibrary=EXPLICIT), ElemType(elemCode=C3D4, elemLibrary=EXPLICIT, 
    secondOrderAccuracy=OFF, distortionControl=DEFAULT)), regions=(
    mdb.models['Model-1'].parts['cfr'].cells.getSequenceFromMask(('[#1 ]', ), 
    ), ))
# Save by 14analysis on 2018_10_30-15.26.47; build 6.12-1 2012_03_13-20.23.18 119612

