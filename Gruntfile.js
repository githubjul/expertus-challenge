module.exports = function (grunt) {
// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		watch: {
			css: {
				files: './static-src/scss/**/*.scss',
				tasks: ['compass', 'clean:css', 'concat:css', 'cssmin']
			},
			js: {
				files: ['./static-src/js-development/**/*.js'],
				tasks: ['clean:js', 'concat:js', 'uglify'],
				options: {
					spawn: false
				}
			}
		},
		concat: {
			options: {
				separator: '\n\n'
			},
			js: {
				files: {
					'./resources/static/js/global.js': [
						"./static-src/js-development/**/*.js"
					]
				}
			},
			css: {
				files: {
					'./resources/static/css/global.css': [
						"./static-src/scss/css-generated/global.css"
					]
				}
			}

		},
		uglify: {
			options: {mangle: false},
			global: {
				files: {'./resources/static/js/global.min.js': ['./resources/static/js/global.js']

				}
			}
		},
		cssmin: {
			global: {
				files: [{
						expand: true,
						src: ['./resources/static/css/global.css', '!*.min.css'],
						dest: './',
						ext: '.min.css'
					}]
			}
		},
		clean: {
			js: ["./resources/static/js"],
			css: ["./resources/static/css"]
		},
		compass: {// Task
			dist: {// Target
				options: {// Target options
					sassDir: './static-src/scss',
					cssDir: './static-src/scss/css-generated'
				}
			}
		}
	});
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-compass');
	grunt.file.setBase('src/main/');
	grunt.registerTask('default', ['clean', 'compass', 'concat', 'uglify', 'cssmin', 'watch']);
};


