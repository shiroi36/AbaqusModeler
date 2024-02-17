
#-------------------------------------------------------------------------------------------
#パートモジュール
#スケッチの単なる押し出し。ここではtest00スケッチ3000mm押し出している。
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=200.0)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=STANDALONE)
s.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches[s0])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseShellExtrude(sketch=s, depth=lb)
s.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']


#データム平面の使用
p = mdb.models['Model-1'].parts[cpartname]
v1 = p.vertices
p.DatumPlaneByThreePoints(point1=v1[10], point2=v1[12], point3=v1[13])

p = mdb.models['Model-1'].parts[cpartname]
v, e = p.vertices, p.edges
p.DatumPlaneByThreePoints(point1=v[8], point2=p.InterestingPoint(edge=e[10], 
rule=MIDDLE), point3=p.InterestingPoint(edge=e[14], rule=MIDDLE))
p = mdb.models['Model-1'].parts[cpartname]
v1, e = p.vertices, p.edges
p.DatumAxisByTwoPoint(point1=v1[8], point2=p.InterestingPoint(edge=e[10], 
rule=MIDDLE))
p = mdb.models['Model-1'].parts[cpartname]
v2, e1 = p.vertices, p.edges
p.DatumAxisByTwoPoint(point1=v2[6], point2=v2[2])
p = mdb.models['Model-1'].parts[cpartname]
v1 = p.vertices
p.DatumAxisByTwoPoint(point1=v1[10], point2=v1[11])
p = mdb.models['Model-1'].parts[cpartname]
v2 = p.vertices
p.DatumAxisByTwoPoint(point1=v2[5], point2=v2[1])
#データムポイント
p = mdb.models['Model-1'].parts[cpartname]
p.DatumPointByCoordinate(coords=(0.0, 0.0, 0.0))
p = mdb.models['Model-1'].parts[cpartname]
p.DatumPointByCoordinate(coords=(0.0, 0.0, lb2))


#フランジ面の穴あけ
p = mdb.models['Model-1'].parts[cpartname]
d2 = p.datums
t = p.MakeSketchTransform(sketchPlane=d2[3], sketchUpEdge=d2[4], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(0.0, h/2, lb/2))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4989.44, gridSpacing=124.73, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches[s1])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts[cpartname]
d1 = p.datums
p.CutExtrude(sketchPlane=d1[3], sketchUpEdge=d1[4], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s, flipExtrudeDirection=ON)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


#ウェブ面の穴あけ
p = mdb.models['Model-1'].parts[cpartname]
d2 = p.datums
t = p.MakeSketchTransform(sketchPlane=d2[2], sketchUpEdge=d2[5], 
sketchPlaneSide=SIDE1, sketchOrientation=RIGHT, origin=(100.0, 0.0,lb/2))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4989.44, gridSpacing=124.73, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches[s3])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts[cpartname]
d1 = p.datums
p.CutExtrude(sketchPlane=d1[2], sketchUpEdge=d1[5], sketchPlaneSide=SIDE1, 
sketchOrientation=RIGHT, sketch=s1, flipExtrudeDirection=OFF)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']

#フランジ面のスケッチ
p = mdb.models['Model-1'].parts[cpartname]
f, e1, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[3], sketchUpEdge=d1[6], 
sketchPlaneSide=SIDE2, origin=(0.0,h/2, lb/2))
s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4989.44, gridSpacing=124.73, transform=t)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s1, filter=COPLANAR_EDGES)
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches[s2])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#1f ]', ), )
f1, e, d2 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d2[3], sketchUpEdge=d2[6], 
faces=pickedFaces, sketchPlaneSide=SIDE2, sketch=s1)
s1.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


#ウェブ面のスケッチ
p = mdb.models['Model-1'].parts[cpartname]
f, e1, d1 = p.faces, p.edges, p.datums
t = p.MakeSketchTransform(sketchPlane=d1[2], sketchUpEdge=d1[5], 
sketchPlaneSide=SIDE1, origin=(100.0, 0.0, lb/2))
s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=4989.44, gridSpacing=124.73, transform=t)
g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints
s.setPrimaryObject(option=SUPERIMPOSE)
p = mdb.models['Model-1'].parts[cpartname]
p.projectReferencesOntoSketch(sketch=s, filter=COPLANAR_EDGES)
s.retrieveSketch(sketch=mdb.models['Model-1'].sketches[s4])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedFaces = f.getSequenceFromMask(mask=('[#ffffffff #fff ]', ), )
f1, e, d2 = p.faces, p.edges, p.datums
p.PartitionFaceBySketchThruAll(sketchPlane=d2[2], sketchUpEdge=d2[5], 
faces=pickedFaces, sketchPlaneSide=SIDE1, sketch=s)
s.unsetPrimaryObject()
del mdb.models['Model-1'].sketches['__profile__']


#集合の定義
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getByBoundingBox(-1000,h/2-20,-lb,1000,h/2+20,2*lb,)
p.Set(faces=faces, name='bf1')
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getByBoundingBox(-1000,-h/2-20,-lb,1000,-h/2+20,2*lb,)
p.Set(faces=faces, name='bf2')
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getByBoundingBox(-10,-h/2-20,-lb,10,h/2+20,2*lb,)
p.Set(faces=faces, name='bw')

#サーフェスの定義

p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side12Faces = s.getByBoundingBox(5,h/2-20,lb-375,1000,h/2+20,lb-5,)
p.Surface(side12Faces=side12Faces, name='b1')

p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side12Faces = s.getByBoundingBox(-1000,h/2-20,lb-375,-5,h/2+20,lb-5,)
p.Surface(side12Faces=side12Faces, name='b2')

p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side12Faces = s.getByBoundingBox(5,-h/2-20,lb-375,1000,-h/2+20,lb-5,)
p.Surface(side12Faces=side12Faces, name='b3')

p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side12Faces = s.getByBoundingBox(-1000,-h/2-20,lb-375,-5,-h/2+20,lb-5,)
p.Surface(side12Faces=side12Faces, name='b4')

p = mdb.models['Model-1'].parts[cpartname]
s = p.faces
side12Faces = s.getByBoundingBox(-10,-hs/2+5,lb-375,10,hs/2-5,lb-5,)
p.Surface(side12Faces=side12Faces, name='b5')

#-------------------------------------------------------------------------------------------
#材料の定義。これはSM490材を模したもの

#シェル厚。これはシェルcfの12mm厚
mdb.models['Model-1'].HomogeneousShellSection(name='bf', preIntegrate=OFF, 
material=mat, thicknessType=UNIFORM, thickness=tf, 
thicknessField='', idealization=NO_IDEALIZATION, 
poissonDefinition=DEFAULT, thicknessModulus=None, temperature=GRADIENT, 
useDensity=OFF, integrationRule=SIMPSON, numIntPts=5)


mdb.models['Model-1'].HomogeneousShellSection(name='bw', preIntegrate=OFF, 
material=mat, thicknessType=UNIFORM, thickness=tw, 
thicknessField='', idealization=NO_IDEALIZATION, 
poissonDefinition=DEFAULT, thicknessModulus=None, temperature=GRADIENT, 
useDensity=OFF, integrationRule=SIMPSON, numIntPts=5)



#材料の割り当て。パートモジュールで割り当てた集合に材料を割り当てていくつもり。
p = mdb.models['Model-1'].parts[cpartname]
region = p.sets['bf1']
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='bf', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)
p = mdb.models['Model-1'].parts[cpartname]
region = p.sets['bf2']
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='bf', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)
p = mdb.models['Model-1'].parts[cpartname]
region = p.sets['bw']
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName='bw', offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)



#-------------------------------------------------------------------------------------------

#メッシュの作成
#メッシュ特性の割り当て

p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedRegions = f.getByBoundingBox(-1000,-1000,-lb,1000,1000,2*lb,)
p.setMeshControls(regions=pickedRegions, elemShape=TRI, allowMapped=False)
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedRegions = f.getByBoundingBox(-1000,-1000,-lb,1000,1000,lb-1000,)
p.setMeshControls(regions=pickedRegions, elemShape=TRI, allowMapped=False)
p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
pickedRegions = f.getByBoundingBox(-1000,-1000,lb-900,1000,1000,lb-390,)
p.setMeshControls(regions=pickedRegions, elemShape=QUAD, technique=STRUCTURED)


#メッシュシードの割り当て
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=200.0, deviationFactor=0.1, minSizeFactor=0.1)

p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(-1000,-1000,-lb,1000,1000,lb-1000,)
p.seedEdgeBySize(edges=pickedEdges, size=200.0, deviationFactor=0.1, 
minSizeFactor=0.1, constraint=FINER)

p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(-1000,-1000,lb-400-1.11*h,1000,1000,lb+1000,)
p.seedEdgeBySize(edges=pickedEdges, size=15.0, deviationFactor=0.1, 
minSizeFactor=0.1, constraint=FINER)


p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(10,-1000,lb-395,1000,1000,lb-5,)
p.deleteSeeds(regions=pickedEdges)

p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(-1000,-1000,lb-395,-10,1000,lb-5,)
p.deleteSeeds(regions=pickedEdges)

p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(10,-1000,lb-395,1000,1000,lb-5,)
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(-1000,-1000,lb-395,-10,1000,lb-5,)
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)
p = mdb.models['Model-1'].parts[cpartname]
e = p.edges
pickedEdges = e.getByBoundingBox(-10,-hs/2+5,lb-300,10,hs/2-5,lb-5,)
p.seedEdgeByNumber(edges=pickedEdges, number=8, constraint=FINER)

#メッシュの実行
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()


p = mdb.models['Model-1'].parts['beam']
e = p.edges
edges = e.getSequenceFromMask(mask=('[#c000000 #2000 #400040 #800 ]', ), )
p.Set(edges=edges, name='e')
