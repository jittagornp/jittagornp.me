module.exports = function (grunt) {

    grunt.initConfig({
        /**/
        pkg: grunt.file.readJSON('package.json'),
        htmlmin: {// Task
            dist: {// Target
                options: {// Target options
                    removeComments: true,
                    collapseWhitespace: true,
                    minifyJS: true,
                    minifyCSS: true
                },
                files: {// Dictionary of files
                    'src/main/webapp/index.html': 'src/main/webapp/index.origin.html' // 'destination': 'source'
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-htmlmin');

    grunt.registerTask('default', [
        'htmlmin'
    ]);
};