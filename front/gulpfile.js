var gulp        = require('gulp'),
    scss        = require('gulp-scss'),
    browserSync = require('browser-sync'),
    concat      = require('gulp-concat'),
    uglify      = require('gulp-uglifyjs'),
    cssnano     = require('gulp-cssnano'),
    rename      = require('gulp-rename'),
    del         = require('del'),
    imagemin    = require('gulp-imagemin'),
    pngquant    = require('imagemin-pngquant'),
    cache       = require('gulp-cache'),
    autoprefixer = require('gulp-autoprefixer');


gulp.task('scss',function(){
    return gulp.src(['app/scss/a.scss'])
        .pipe(scss())
        .pipe(concat('main.css'))
        .pipe(gulp.dest('dist/css'))
        .pipe(browserSync.reload({stream:true}))
});

gulp.task('scripts', function () {
    return gulp.src([
        'node_modules/angular/angular.min.js',
        'node_modules/angular-route/angular-route.js',
        'app/js/**/*.js',
    ])
    //.pipe(concat('libs.min.js'))
    //.pipe(uglify())
    .pipe(concat('main.js'))
    .pipe(gulp.dest('dist/js'));
});
/*
gulp.task('css-libs',['scss'] ,function () {
    return gulp.src('app/css/libs.css')
        .pipe(cssnano())
        .pipe(rename({suffix:'.min'}))
        .pipe(gulp.dest('app/css'));
});
*/
gulp.task('browser-sync',function(){
   browserSync({
       server: {
           baseDir: ('app')
       },
       notify:false
   });
});

gulp.task('clean', function () {
    return del.sync('dist');
});

gulp.task('clear', function () {
    return cache.clearAll();
});

gulp.task('img', function () {
    return gulp.src('app/img/**/*')
        /*.pipe(cache(imagemin({
            interlaced: true,
            progressive: true,
            svgoPlugins: [{removeViewBox: false}],
            use:[pngquant()]
        })))*/
        .pipe(gulp.dest('dist/img'));
});

gulp.task('watch', ['browser-sync','scripts'],function(){
    gulp.watch('app/scss/**/*.scss', ['scss']);
    gulp.watch('app/*.html',browserSync.reload);
    gulp.watch('app/js/**/*.js',browserSync.reload);
});

gulp.task('default',['clean', 'img', 'scss', 'scripts'], function () {

    var buildFonts = gulp.src('app/fonts/**/*')
        .pipe(gulp.dest('dist/fonts'));

    var buildHtml = gulp.src('app/*.html')
        .pipe(gulp.dest('dist/'));
});

