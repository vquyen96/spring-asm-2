package t1708e.springasm2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708e.springasm2.entity.Clazz;
import t1708e.springasm2.entity.Student;
import t1708e.springasm2.repository.ClazzRepository;
import t1708e.springasm2.repository.StudentRepository;
import t1708e.springasm2.service.ClazzService;
import t1708e.springasm2.service.StudentService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/clazz")
public class ClazzController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ClazzService clazzService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable int id, Model model) {
        Clazz clazz = clazzService.getById(id);
        List<Student> studentList = studentService.getAll();
        if (clazz == null) {
            return "error/404";
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("clazz", clazz);
        return "clazz/detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addStudent")
    public String addStudent(int[] studentIds, int clazzId) {
        Clazz clazz = clazzService.getById(clazzId);
        clazz.removeAllStudent();

        if (studentIds != null && studentIds.length > 0) {
            List<Integer> intList = new ArrayList<Integer>();
            for (int i : studentIds)
            {
                intList.add(i);
            }
            List<Student> studentList = studentRepository.findAllById(intList);
            for (Student student: studentList
            ) {
                clazz.addStudent(student);
            }
            clazzRepository.save(clazz);
        }
        return "redirect:/clazz/"+clazzId;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("clazz", new Clazz());
        return "clazz/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(Model model, @Valid Clazz clazz, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clazz", clazz);
            return "clazz/form";
        }
        clazzService.create(clazz);
        return "redirect:/home";
    }
}
